package com.codestates.question;

import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import com.codestates.member.entity.Member;
import com.codestates.member.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Transactional
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final MemberService memberService;

    public QuestionService(QuestionRepository questionRepository, MemberService memberService) {
        this.questionRepository = questionRepository;
        this.memberService = memberService;
    }

    public Question createQuestion(Question question, long memberId) {
        Member findMember = memberService.findVerifiedMember(memberId);
        question.setMember(findMember);
        return questionRepository.save(question);
    }

    public Question updateQuestion(Question question, long memberId) {
        Question findQuestion = findVerifiedQuestion(question.getQuestionId()); // 수정할 질문 찾아오기
        verifyMember(memberId, findQuestion);

        Optional.ofNullable(question.getTitle())
                .ifPresent(findQuestion::setTitle); // 제목
        Optional.ofNullable(question.getBody())
                .ifPresent(findQuestion::setBody); // 내용
//        Optional.ofNullable(question.getTag()) // 태그
//                .ifPresent(findQuestion.getTag);

        findQuestion.setModifiedAt(LocalDateTime.now());

        return questionRepository.save(findQuestion); // 수정 시간 업데이트
    }

    /*public Question upVote(Question question, long questionId) {
        Question findQuestion = findVerifiedQuestion(questionId);
        findQuestion.setVote(findQuestion.getVote() + 1);

        return questionRepository.save(findQuestion);
    }

    public Question downVote(Question question, long questionId) {
        Question findQuestion = findVerifiedQuestion(questionId);
        findQuestion.setVote(findQuestion.getVote() - 1);

        return questionRepository.save(findQuestion);
    }*/

    public Question updateVote(Question question, long questionId) { // Vote Count 값만 변경
        Question findQuestion = findVerifiedQuestion(questionId);

        // vote 변경
        findQuestion.setVote(question.getVote());

        return questionRepository.save(findQuestion);
    }

    public Question updateView(Question question, long questionId) {
        Question findQuestion = findVerifiedQuestion(questionId);
        findQuestion.setView(findQuestion.getView() + 1);

        return questionRepository.save(findQuestion);
    }

    public Question findQuestion(long questionId) {
        return findVerifiedQuestion(questionId);
    }

    public Page<Question> findQuestions(int page, int size) { // 페이징 처리 및 ID 내림차순 정렬
        return questionRepository.findAll(
                PageRequest.of(page, size, Sort.by("questionId").descending())
        );
    }

    public void deleteQuestion(long questionId) {
        Question question = findVerifiedQuestion(questionId);
        questionRepository.delete(question);
    }

    public void verifyMember(long memberId, Question question) {
        Long thisId = question.getMember().getMemberId();
        if (thisId != memberId) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_ALLOWED);
        }
    }
    public Question findVerifiedQuestion(long questionId) { // questionId로 쿼리
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        Question findQuestion = // 에러 핸들링 상의 해야 됨
                optionalQuestion.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
        return findQuestion;
    }
}
