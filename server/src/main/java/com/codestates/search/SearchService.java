package com.codestates.search;

import com.codestates.member.entity.Member;
import com.codestates.member.service.MemberService;
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
    private final MemberService memberService;

    public SearchService(SearchRepository searchRepository, MemberService memberService) {
        this.searchRepository = searchRepository;
        this.memberService = memberService;
    }

    public Page<Question> findContent(String content, int page, int size) {
        if(content.charAt(0) == '[' && content.charAt(content.length() - 1) == ']') {
            return findTag(content.substring(1, content.length() - 1), page, size);
        } else if(content.substring(0,4).equals("user")) {
            System.out.println("substring : " + content.substring(5));
            return findUser(Long.parseLong(content.substring(5)), page, size);
        }

        return findBody(content, page, size);
    }

    private Page<Question> findTag(String tagName, int page, int size) { // 태그로 검색
        return null;
    }

    private Page<Question> findUser(long memberId, int page, int size) { // 유저로 검색
        Member findMember = memberService.findVerifiedMember(memberId);
        return searchRepository.findAllByMember(findMember, PageRequest.of(page, size, Sort.by("vote").descending()));
    }

    private Page<Question> findBody(String content, int page, int size) { // 질문 내용 검색
        return searchRepository.findAllByBodyContaining(content, PageRequest.of(page, size, Sort.by("vote").descending()));
    }
}
