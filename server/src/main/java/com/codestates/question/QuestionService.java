package com.codestates.question;

import com.codestates.answer.entity.Answer;
import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
import com.codestates.member.entity.Member;
import com.codestates.member.service.MemberService;
import com.codestates.tag.*;
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
    private final TagController tagController;
    private final TagDto.Post tagDtoPost;
    private final TagService tagService;
    private final TagRepository tagRepository;

    public QuestionService(QuestionRepository questionRepository, MemberService memberService, TagController tagController, TagDto.Post tagDtoPost, TagService tagService, TagRepository tagRepository) {
        this.questionRepository = questionRepository;
        this.memberService = memberService;
        this.tagController = tagController;
        this.tagDtoPost = tagDtoPost;
        this.tagService = tagService;
        this.tagRepository = tagRepository;
    }

    public Question createQuestion(Question question, long memberId) {

        Member findMember = memberService.findVerifiedMember(memberId);
        question.setMember(findMember);

        String tagBody = question.getTagBody(); // 태그 생성 부분
        List<String> list = new ArrayList<>();
        System.out.println(tagBody);

        Arrays.stream(tagBody.split(","))
                .map(a -> Arrays.stream(a.trim()
                                .split(" "))
                        .flatMap(b -> Arrays.stream(b.split(", "))))
                .flatMap(a -> a)
                .distinct()
                .filter(a -> !Objects.equals(a, ""))
                .map(String::toLowerCase)
                .forEach(list::add);

        System.out.println(list);
        question.setTagList(list);
        return questionRepository.save(question);
    }

    public Question updateQuestion(Question question, long memberId) {
        Question findQuestion = findVerifiedQuestion(question.getQuestionId()); // 수정할 질문 찾아오기
        verifyMember(memberId, findQuestion);

        Optional.ofNullable(question.getTitle())
                .ifPresent(findQuestion::setTitle); // 제목
        Optional.ofNullable(question.getBody())
                .ifPresent(findQuestion::setBody); // 내용

        String tagBody = question.getTagBody();
        List<String> list = new ArrayList<>(); // 태그 수정 부분

        Arrays.stream(tagBody.split(","))
                .map(a -> Arrays.stream(a.trim()
                                .split(" "))
                        .flatMap(b -> Arrays.stream(b.split(", "))))
                .flatMap(a -> a)
                .distinct()
                .filter(a -> !Objects.equals(a, ""))
                .map(String::toLowerCase)
                .forEach(list::add);

        findQuestion.setTagBody(question.getBody());
        findQuestion.setTagList(list);

        return questionRepository.save(findQuestion);
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

    public Page<Question> findQuestionsByTagBody(String tagBody, int page, int size) {
        return questionRepository.findQuestionsByTagBody(
                tagBody, PageRequest.of(page, size, Sort.by("question_id").ascending()));
    }
}
