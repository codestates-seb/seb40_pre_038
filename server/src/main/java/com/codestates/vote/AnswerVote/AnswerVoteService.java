package com.codestates.vote.AnswerVote;

import com.codestates.answer.entity.Answer;
import com.codestates.answer.repository.AnswerRepository;
import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import com.codestates.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerVoteService {
    private final AnswerRepository answerRepository;
    private final AnswerVoteRepository answerVoteRepository;
    private final UserService userService;

    public AnswerVoteService(AnswerRepository answerRepository, AnswerVoteRepository answerVoteRepository, UserService userService) {
        this.answerRepository = answerRepository;
        this.answerVoteRepository = answerVoteRepository;
        this.userService = userService;
    }

    public void postVote(long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);

        Answer findAnswer = optionalAnswer.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));

        long loginUserId = userService.getLoginUser().getUserId(); // 로그인 유저
        AnswerVote findAnswerVote = answerVoteRepository.findByAnswerAndUserId(findAnswer, loginUserId);

        if(findAnswerVote == null) { // 투표한 적 없음
            findAnswerVote = new AnswerVote();
            findAnswerVote.setAnswer(findAnswer);
            findAnswerVote.setUserId(loginUserId);
            answerVoteRepository.save(findAnswerVote);
        } else { // 이미 투표함
            throw new BusinessLogicException(ExceptionCode.VOTED);
        }
    }
}
