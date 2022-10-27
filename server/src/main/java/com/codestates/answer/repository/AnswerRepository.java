package com.codestates.answer.repository;

import com.codestates.answer.entity.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query(nativeQuery = true, value = "select * from Answer a where a.status > 0 and a.question_id = :questionId")
    Page<Answer> findAll(long questionId, Pageable pageable);
}
