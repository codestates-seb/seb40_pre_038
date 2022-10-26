package com.codestates.question;

import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question updateQuestion(Question question, long questionId) {
        Question findQuestion = findVerifiedQuestion(questionId); // 수정할 질문 찾아오기

        Optional.ofNullable(question.getTitle())
                .ifPresent(findQuestion::setTitle); // 제목
        Optional.ofNullable(question.getBody())
                .ifPresent(findQuestion::setBody); // 내용
//        Optional.ofNullable(question.getTag()) // 태그
//                .ifPresent(findQuestion.getTag);

        return questionRepository.save(question);
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

    public Question findVerifiedQuestion(long questionId) { // questionId로 쿼리
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        Question findQuestion = // 에러 핸들링 상의 해야 됨
                optionalQuestion.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findQuestion;
    }
}
