package com.codestates.vote.AnswerVote;

import com.codestates.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerVoteRepository extends JpaRepository<AnswerVote, Long> {
    AnswerVote findByAnswerAndUserId(Answer answer, long userId);
}
