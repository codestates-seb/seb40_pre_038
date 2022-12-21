package com.codestates.search;

import com.codestates.user.service.UserService;
import com.codestates.question.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Transactional
@Service
public class SearchService {
    private final SearchRepository searchRepository;
    private final UserService userService;

    public SearchService(SearchRepository searchRepository, UserService userService) {
        this.searchRepository = searchRepository;
        this.userService = userService;
    }

    public Page<Question> findContent(String all, String tab, int page, int size) {
        String tag = null;
        String userNickName = null;
        List<String> contents = new ArrayList<>();

        //all = all.replaceAll( "[^\uAC00-\uD7A30-9a-zA-Z\\[\\]\\s]", ""); // 특수문자 제거 ([,] 빼고)

        List<String> findAll = Arrays.asList(all.split(" "));
        for(String find : findAll) {
            System.out.println("find:"+find);
            if(find.length() >= 3 && find.charAt(0) == '[' && find.charAt(find.length() - 1) == ']') // 태그 검색일 경우
                tag = find.substring(1, find.length() - 1);
            else if (find.length() >= 6 && find.substring(0,5).equals("user:")) // 유저 검색일 경우
                userNickName = find.substring(5);
            else
                contents.add(find); // 일반 검색일 경우 keyword로 나누기
        }
        System.out.println("TAG:"+tag + ",USER:"+userNickName);

        return findTagUserContent(tag, userNickName, contents, tab, page, size); // 검색
    }

    private Page<Question> findTagUserContent(String tag, String userNickName, List<String> contents, String tab, int page, int size) { // 질문 내용 검색
        Specification<Question> spec = null;
        if(tag != null) // tag 검색이 있을 경우
            spec = (spec == null? SearchSpecification.containsTag(tag) : spec.and(SearchSpecification.containsTag(tag)));

        if(userNickName != null) { // user 검색이 있을 경우
            //User user = userService.findVerifiedUser(userId);
            spec = (spec == null ? SearchSpecification.equalToUser(userNickName) : spec.and(SearchSpecification.equalToUser(userNickName)));
        }

        if(contents != null) { // 검색어가 있을 경우
            Specification<Question> contentSpec = null;
            for(String word : contents) {
                Specification<Question> wordSpec = SearchSpecification.containsTitleProblemExpect(word);
                contentSpec = (contentSpec == null ? wordSpec : contentSpec.or(wordSpec));
            }
            spec = (spec == null? contentSpec : spec.and(contentSpec));
        }

        if(tab.equals("score")) // 투표수로 정렬
           return searchRepository.findAll(spec, PageRequest.of(page, size, Sort.by("vote").descending()));

        return searchRepository.findAll(spec, PageRequest.of(page, size, Sort.by("questionId").descending()));
    }
}
