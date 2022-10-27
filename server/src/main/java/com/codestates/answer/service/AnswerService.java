package com.codestates.answer.service;

import com.codestates.answer.entity.Answer;
import com.codestates.answer.repository.AnswerRepository;
import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        Answer savedAnswer = answerRepository.save(answer);
        
        return savedAnswer;
    }

    public Answer updateAnswer(Answer answer) {
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());

        // 내용 변경
        Optional.ofNullable(answer.getBody())
                .ifPresent(body -> findAnswer.setBody(body));
        
        // vote count 변경은 updateVote 메서드에서 진행
        
        // 답변 상태 변경은 updateStatus 메서드에서 진행
        
        return answerRepository.save(findAnswer);
    }
    
    public Answer findAnswer(long answerId) {
        return findVerifiedAnswer(answerId);
    }
    
    public Page<Answer> findAnswers(long questionId, int page, int size) {
        // 삭제된 답변은 보이지 않음
        return answerRepository.findAllByStatus(PageRequest.of(page, size, Sort.by("status").ascending()));
    }

    public void deleteAnswer(long answerId) {
        Answer findAnswer = findVerifiedAnswer(answerId);

        // 답변을 삭제되었을 경우 기록은 남아 있음
        findAnswer.setAnswerStatus(Answer.AnswerStatus.ANSWER_DELETE);
        answerRepository.save(findAnswer);
    }

    public Answer updateVote(Answer answer) { // Vote Count 값만 변경
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());

        // vote Count 변경
        findAnswer.setVote(answer.getVote());

        return answerRepository.save(findAnswer);
    }

    public Answer updateStatus(Answer answer) { // Answer Status만 변경 (채택 or 일반)
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());

        // Answer Status 변경
        findAnswer.setAnswerStatus(answer.getAnswerStatus());

        return answerRepository.save(findAnswer);
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
