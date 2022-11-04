package com.codestates.search;

import com.codestates.user.entity.User;
import com.codestates.user.service.UserService;
import com.codestates.question.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class SearchService {
    private final SearchRepository searchRepository;
    private final UserService userService;

    public SearchService(SearchRepository searchRepository, UserService userService) {
        this.searchRepository = searchRepository;
        this.userService = userService;
    }

    public Page<Question> findContent(String content, String tab, int page, int size) {
        if(content.length() >= 3 && content.charAt(0) == '[' && content.charAt(content.length() - 1) == ']') {
            String substring = content.substring(1, content.length() - 1).replaceAll(" ", ""); // 검색어 공백 제거
            return findTag(substring.toLowerCase(), tab, page, size);
        } else if(content.length() >= 6 && content.substring(0,5).equals("user:")) {
            return findUser(Long.parseLong(content.substring(5)), tab, page, size);
        }

        return findBody(content, tab, page, size);
    }

    private Page<Question> findTag(String tagBody, String tab, int page, int size) { // 태그로 검색
        if(tab.equals("score"))
            return searchRepository.findAllByTagList(tagBody, PageRequest.of(page, size, Sort.by("vote").descending()));
        return searchRepository.findAllByTagList(tagBody, PageRequest.of(page, size, Sort.by("questionId").descending()));
    }

    private Page<Question> findUser(long userId, String tab, int page, int size) { // 유저로 검색
        User findUser = userService.findVerifiedUser(userId);
        if(tab.equals("score")) return searchRepository.findAllByUser(findUser, PageRequest.of(page, size, Sort.by("vote").descending()));
        return searchRepository.findAllByUser(findUser, PageRequest.of(page, size, Sort.by("questionId").descending()));
    }

    private Page<Question> findBody(String content, String tab, int page, int size) { // 질문 내용 검색
        if(tab.equals("score")) return searchRepository.findAllByTitleContainingOrProblemContainingOrExpectContaining(
                content, content, content, PageRequest.of(page, size, Sort.by("vote").descending()));
        return searchRepository.findAllByTitleContainingOrProblemContainingOrExpectContaining(
                content, content, content, PageRequest.of(page, size, Sort.by("questionId").descending()));
    }
}
