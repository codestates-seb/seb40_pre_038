package com.codestates.search;

import com.codestates.member.entity.Member;
import com.codestates.question.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SearchRepository extends JpaRepository<Question, Long> {

//    @Query(nativeQuery = true,
//            value = "select question from Question question where question.problem like?body or question.expect like?body")
    Page<Question> findAllByProblemContaining(String body, Pageable pageable);

    Page<Question> findAllByMember(Member member, Pageable pageable);

    Page<Question> findAllByTagBodyContaining(String tagBody, Pageable pageable);
}
