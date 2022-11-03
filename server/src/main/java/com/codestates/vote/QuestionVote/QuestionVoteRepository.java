package com.codestates.vote.QuestionVote;

import com.codestates.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionVoteRepository extends JpaRepository<QuestionVote, Long> {
    QuestionVote findByQuestionAndUserId(Question question, long userId);
}
