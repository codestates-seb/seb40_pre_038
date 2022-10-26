package com.codestates.answer.repository;

import com.codestates.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    //List<Answer> findAllByQuestion(Question question);
}
