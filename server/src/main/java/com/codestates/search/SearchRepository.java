package com.codestates.search;

import com.codestates.member.entity.Member;
import com.codestates.question.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchRepository extends JpaRepository<Question, Long> {
    Page<Question> findAllByBodyContaining(String body, Pageable pageable);

    Page<Question> findAllByMember(Member member, Pageable pageable);
}
