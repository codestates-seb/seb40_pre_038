package com.codestates.answer.service;

import com.codestates.answer.entity.Answer;
import com.codestates.answer.repository.AnswerRepository;
import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    //private final MemberService memberService;
    //private final QuestionService questionService;

    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;

        // TODO: memberservice, questionservice DI
    }

    public Answer createAnswer(Answer answer) {
        verifyAnswer(answer);
        Answer savedAnswer = answerRepository.save(answer);

        savedAnswer.setVoteCounts(0);

        // TODO: 업데이트 해야되는 entity가 있는지 확인
        
        return savedAnswer;
    }

    public Answer updateAnswer(Answer answer) {
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());

        Optional.ofNullable(answer.getContents())
                .ifPresent(contents -> findAnswer.setContents(contents));
        
        // TODO: vote count 변경
        Optional.ofNullable(answer.getVoteCounts())
                        .ifPresent(voteCounts -> findAnswer.setVoteCounts(voteCounts));
        
        Optional.ofNullable(answer.getAnswerStatus())
                .ifPresent(answerStatus -> findAnswer.setAnswerStatus(answerStatus));
        
        return answerRepository.save(findAnswer);
    }
    
    public Answer findAnswer(long answerId) {
        return findVerifiedAnswer(answerId);
    }
    
    public List<Answer> findAnswers(int size) {
        // TODO: findAllByQuestion <- 이걸로 바꾸기

        return null;
    }

    public void deleteAnswer(long answerId) {
        Answer findAnswer = findVerifiedAnswer(answerId);

        // 답변을 삭제되었을 경우 기록은 남아 있음
        findAnswer.setAnswerStatus(Answer.AnswerStatus.ANSWER_DELETE);
        answerRepository.save(findAnswer);
    }
    
    private void verifyAnswer(Answer answer) {
        // TODO: 회원이 존재하는지 확인
        
        // TODO: 문제가 존재하는지 확인
    }

    private Answer findVerifiedAnswer(long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);

        Answer findAnswer = optionalAnswer.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));

        // 삭제된 답변인지 확인
        int step = findAnswer.getAnswerStatus().getStatusNumber();
        if(step <= 0) throw new BusinessLogicException(ExceptionCode.ANSWER_DELETED);

        return findAnswer;
    }
}
