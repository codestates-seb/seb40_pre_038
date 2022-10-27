package com.codestates.answer.repository;

import com.codestates.answer.entity.Answer;
import com.codestates.question.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query(nativeQuery = true, value = "select * from answer where status > 0")
    Page<Answer> findAllByStatus(Pageable pageable);
}
