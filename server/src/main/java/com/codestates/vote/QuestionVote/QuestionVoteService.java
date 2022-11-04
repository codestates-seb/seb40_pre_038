package com.codestates.vote.QuestionVote;

import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import com.codestates.question.Question;
import com.codestates.question.QuestionRepository;
import com.codestates.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionVoteService {
    private final QuestionRepository questionRepository;
    private final QuestionVoteRepository questionVoteRepository;
    private final UserService userService;

    public QuestionVoteService(QuestionRepository questionRepository, QuestionVoteRepository questionVoteRepository, UserService userService) {
        this.questionRepository = questionRepository;
        this.questionVoteRepository = questionVoteRepository;
        this.userService = userService;
    }

    public void postVote(long questionId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        
        Question findQuestion = optionalQuestion.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        long loginUserId = userService.getLoginUser().getUserId(); // 로그인 유저
        QuestionVote findQuestionVote = questionVoteRepository.findByQuestionAndUserId(findQuestion, loginUserId);
        
        if(findQuestionVote == null) { // 투표한 적 없음
            findQuestionVote = new QuestionVote();
            findQuestionVote.setQuestion(findQuestion);
            findQuestionVote.setUserId(loginUserId);
            questionVoteRepository.save(findQuestionVote);
        } else { // 이미 투표함
            throw new BusinessLogicException(ExceptionCode.VOTED);
        }
    }
}
