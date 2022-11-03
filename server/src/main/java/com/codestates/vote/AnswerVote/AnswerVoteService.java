package com.codestates.vote.AnswerVote;

import com.codestates.answer.entity.Answer;
import com.codestates.answer.repository.AnswerRepository;
import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerVoteService {
    private final AnswerRepository answerRepository;
    private final AnswerVoteRepository answerVoteRepository;

    public AnswerVoteService(AnswerRepository answerRepository, AnswerVoteRepository answerVoteRepository) {
        this.answerRepository = answerRepository;
        this.answerVoteRepository = answerVoteRepository;
    }

    public void postVote(long answerId, long userId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);

        Answer findAnswer = optionalAnswer.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));

        AnswerVote findAnswerVote = answerVoteRepository.findByAnswerAndUserId(findAnswer, userId);

        if(findAnswerVote == null) { // 투표한 적 없음
            findAnswerVote = new AnswerVote();
            findAnswerVote.setAnswer(findAnswer);
            findAnswerVote.setUserId(userId);
            answerVoteRepository.save(findAnswerVote);
        } else { // 이미 투표함
            throw new BusinessLogicException(ExceptionCode.VOTED);
        }
    }
}
