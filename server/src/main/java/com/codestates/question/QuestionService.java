package com.codestates.question;

import com.codestates.answer.entity.Answer;
import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import com.codestates.member.entity.Member;
import com.codestates.member.service.MemberService;
//import com.codestates.tag.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final MemberService memberService;
//    private final TagController tagController;
//    private final TagDto.Post tagDtoPost;
//    private final TagService tagService;
//    private final TagRepository tagRepository;


    public QuestionService(QuestionRepository questionRepository, MemberService memberService) {
        this.questionRepository = questionRepository;
        this.memberService = memberService;
    }

    public Question createQuestion(Question question, long memberId) {

        Member findMember = memberService.findVerifiedMember(memberId);
        question.setMember(findMember);

        String tagBody = question.getTagBody(); // 태그 생성 부분

//        Arrays.stream(tagBody.split(","))
//                .map(a -> Arrays.stream(a.trim()
//                                .split(" "))
//                        .flatMap(b -> Arrays.stream(b.split(", "))))
//                .flatMap(a -> a)
//                .distinct()
//                .filter(a -> !Objects.equals(a, ""))
//                .map(String::toLowerCase)
//                .forEach(list::add);
        List<String> list = new ArrayList<>(Arrays.asList(tagBody.split(", ")));

        question.setTagList(list);

        Question savedQuestion = questionRepository.save(question);

        System.out.printf("\n회원 번호: " + memberId + '\n' +
                "사용자 이름: " + findMember.getNickName() + '\n' +
                question.getQuestionId() + "번 질문 등록 완료.\n\n");

        return savedQuestion;
    }

    public Question updateQuestion(Question question, long memberId) {
        Question findQuestion = findVerifiedQuestion(question.getQuestionId()); // 수정할 질문 찾아오기
        Member findMember = memberService.findVerifiedMember(memberId);
        verifyMember(memberId, findQuestion);

        Optional.ofNullable(question.getTitle())
                .ifPresent(findQuestion::setTitle); // 제목
        Optional.ofNullable(question.getProblem())
                .ifPresent(findQuestion::setProblem); // 문제
        Optional.ofNullable(question.getExpect())
                .ifPresent(findQuestion::setExpect); // 기대

        String tagBody = question.getTagBody();
//        List<String> list = new ArrayList<>(); // 태그 수정 부분

//        Arrays.stream(tagBody.split(","))
//                .map(a -> Arrays.stream(a.trim()
//                                .split(" "))
//                        .flatMap(b -> Arrays.stream(b.split(", "))))
//                .flatMap(a -> a)
//                .distinct()
//                .filter(a -> !Objects.equals(a, ""))
//                .map(String::toLowerCase)
//                .forEach(list::add);
        List<String> list = new ArrayList<>(Arrays.asList(tagBody.split(", ")));

        findQuestion.setTagBody(question.getTagBody());
        findQuestion.setTagList(list);

        Question updatedQuestion = questionRepository.save(findQuestion);

        System.out.printf("\n회원 번호: " + memberId + '\n' +
                "사용자 이름: " + findMember.getNickName() + '\n' +
                question.getQuestionId() + "번 질문 수정 완료.\n\n");

        return updatedQuestion;
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
                PageRequest.of(page, size, Sort.by("questionId").descending()));
    }

    public void deleteQuestion(long questionId) {
        Question question = findVerifiedQuestion(questionId);
        questionRepository.delete(question);

        System.out.printf("\n회원 번호: " + question.getMember().getMemberId() + '\n' +
                "사용자 이름: " + question.getMember().getNickName() + '\n' +
                question.getQuestionId() + "번 질문 삭제 완료.\n\n");
    }

//    public void addQuestionTagConnection(QuestionDto.Post questionPost, Question question) {
//        Question findQuestion = findVerifiedQuestion(question.getQuestionId());
//        Set<QuestionTag> questionTags = new HashSet<>();
//
//        questionPost.getTagIds().stream()
//                .map(tag -> findQuestion.getQuestionTags().add(tag));
//    }

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

//    public Page<Question> findQuestionsByTagBody(int page, int size) {
//        return questionRepository.findAll(
//                PageRequest.of(page, size, Sort.by("questionId").descending()));
////        return questionRepository.findAllByBodyContaining(
////                tagBody, PageRequest.of(page, size, Sort.by("tag_id").ascending()));
//    }
//    public Page<Question> findQuestionsByTagBody(String tagBody, int page, int size) {
////        return questionRepository.findAll(
////                PageRequest.of(page, size, Sort.by("questionId").descending()));
//        return questionRepository.findQuestionsByTagBody(
//                tagBody, PageRequest.of(page, size, Sort.by("tag_id").ascending()));
//    }

//    public List<Integer> findListByTagBody(String tagBody) {
////        return questionRepository.findAll(
////                PageRequest.of(page, size, Sort.by("questionId").descending()));
//        return questionRepository.findListByTagBody(
//                tagBody);
//    }

    public Page<Question> findAllByTagBody(String tagBody, int page, int size) { // 질문 내용 검색
        return questionRepository.findAllByTagBodyContaining(tagBody, PageRequest.of(page, size, Sort.by("vote").descending()));
    }

}
