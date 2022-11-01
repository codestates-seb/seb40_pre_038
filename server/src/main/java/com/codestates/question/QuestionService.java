package com.codestates.question;

import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import com.codestates.user.entity.User;
import com.codestates.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Transactional
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserService userService;

    public QuestionService(QuestionRepository questionRepository, UserService userService) {
        this.questionRepository = questionRepository;
        this.userService = userService;
    }

    public Question createQuestion(Question question, long userId) {

        User findUser = userService.findVerifiedUser(userId);
        question.setUser(findUser);

        String tagBody = question.getTagBody(); // 태그 생성 부분

        List<String> list = new ArrayList<>(Arrays.asList(tagBody.split(", ")));

        question.setTagList(list);

        Question savedQuestion = questionRepository.save(question);

        System.out.printf("\n회원 번호: " + userId + '\n' +
                "사용자 이름: " + findUser.getNickName() + '\n' +
                question.getQuestionId() + "번 질문 등록 완료.\n\n");

        return savedQuestion;
    }

    public Question updateQuestion(Question question, long userId) {
        Question findQuestion = findVerifiedQuestion(question.getQuestionId()); // 수정할 질문 찾아오기
        User findUser = userService.findVerifiedUser(userId);
        verifyUser(userId, findQuestion);

        Optional.ofNullable(question.getTitle())
                .ifPresent(findQuestion::setTitle); // 제목
        Optional.ofNullable(question.getProblem())
                .ifPresent(findQuestion::setProblem); // 문제
        Optional.ofNullable(question.getExpect())
                .ifPresent(findQuestion::setExpect); // 기대

        String tagBody = question.getTagBody();

        List<String> list = new ArrayList<>(Arrays.asList(tagBody.split(", ")));

        findQuestion.setTagBody(question.getTagBody());
        findQuestion.setTagList(list);

        Question updatedQuestion = questionRepository.save(findQuestion);

        System.out.printf("\n회원 번호: " + userId + '\n' +
                "사용자 이름: " + findUser.getNickName() + '\n' +
                question.getQuestionId() + "번 질문 수정 완료.\n\n");

        return updatedQuestion;
    }

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
                PageRequest.of(page, size, Sort.by("questionId").descending()));
    }

    public void deleteQuestion(long questionId) {
        Question question = findVerifiedQuestion(questionId);
        questionRepository.delete(question);

        System.out.printf("\n회원 번호: " + question.getUser().getUserId() + '\n' +
                "사용자 이름: " + question.getUser().getNickName() + '\n' +
                question.getQuestionId() + "번 질문 삭제 완료.\n\n");
    }

    public void verifyUser(long userId, Question question) {
        Long thisId = question.getUser().getUserId();
        if (thisId != userId) {
            throw new BusinessLogicException(ExceptionCode.USER_NOT_ALLOWED);
        }
    }

    public Question findVerifiedQuestion(long questionId) { // questionId로 쿼리
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        return optionalQuestion.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
    }

    public Page<Question> findAllByTagBody(String tagBody, int page, int size) { // 질문 내용 검색
        return questionRepository.findAllByTagBodyContaining(tagBody,
                PageRequest.of(page, size, Sort.by("vote").descending()));
    }

    public Page<Question> findTopQuestions(String tab, int page, int size) { // Top Questions
        LocalDateTime current = LocalDateTime.now();
        LocalDateTime week = current.minusWeeks(1);
        LocalDateTime month = current.minusMonths(1);

        if(tab.equals("week"))
            return questionRepository.findAllByModifiedAtBetween(week, current, PageRequest.of(page, size, Sort.by("questionId").descending()));
        else if(tab.equals("month"))
            return questionRepository.findAllByModifiedAtBetween(month, current, PageRequest.of(page, size, Sort.by("questionId").descending()));

        return questionRepository.findAllByQuestionID(PageRequest.of(page, size, Sort.by("question_id").descending()));
    }

    public Page<Question> findAllQuestions(String tab, int page, int size) { // All Questions
        if(tab.equals("score"))
            return questionRepository.findAll(PageRequest.of(page, size));

        return questionRepository.findAll(PageRequest.of(page, size, Sort.by("questionId").descending()));
    }
}
