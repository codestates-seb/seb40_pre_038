package com.codestates.vote.QuestionVote;

import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import com.codestates.question.Question;
import com.codestates.question.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionVoteService {
    private final QuestionRepository questionRepository;
    private final QuestionVoteRepository questionVoteRepository;

    public QuestionVoteService(QuestionRepository questionRepository, QuestionVoteRepository questionVoteRepository) {
        this.questionRepository = questionRepository;
        this.questionVoteRepository = questionVoteRepository;
    }
    
    public void postVote(long questionId, long memberId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        
        Question findQuestion = optionalQuestion.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
        
        QuestionVote findQuestionVote = questionVoteRepository.findByQuestionAndMemberId(findQuestion, memberId);
        
        if(findQuestionVote == null) { // 투표한 적 없음
            findQuestionVote = new QuestionVote();
            findQuestionVote.setQuestion(findQuestion);
            findQuestionVote.setMemberId(questionId);
            questionVoteRepository.save(findQuestionVote);
        } else { // 이미 투표함
            throw new BusinessLogicException(ExceptionCode.VOTED);
        }
    }
}
