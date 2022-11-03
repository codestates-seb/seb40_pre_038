package com.codestates.answer.repository;

import com.codestates.answer.entity.Answer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query(nativeQuery = true,
            value = "select * from Answer a where a.status > 0 and a.question_id = :questionId"
                    + " order by status asc, vote desc, answer_id desc"
    )
    List<Answer> findAll(long questionId);
}
