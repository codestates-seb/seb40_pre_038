package com.codestates.question;

import com.codestates.answer.dto.AnswerBestDto;
import com.codestates.answer.entity.Answer;
import com.codestates.answer.mapper.AnswerMapper;
import com.codestates.answer.service.AnswerService;
import com.codestates.dto.MultiResponseDto;
import com.codestates.dto.SingleResponseDto;
import com.codestates.vote.QuestionVote.QuestionVoteService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://pre-project-038-client.s3-website.ap-northeast-2.amazonaws.com",
        "http://ec2-13-125-208-244.ap-northeast-2.compute.amazonaws.com:8080"})
@Validated
@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionMapper mapper;
    private final AnswerService answerService; // 답변 채택 기능에서 필요
    private final AnswerMapper answerMapper;
    private final QuestionVoteService questionVoteService; // 질문 투표에서 필요

    public QuestionController(QuestionService questionService, QuestionMapper mapper, AnswerService answerService, AnswerMapper answerMapper, QuestionVoteService questionVoteService) {
        this.questionService = questionService;
        this.mapper = mapper;
        this.answerService = answerService;
        this.answerMapper = answerMapper;
        this.questionVoteService = questionVoteService;
    }

    @Secured("ROLE_USER")
    @PostMapping("/add")
    public ResponseEntity postQuestion(@Valid @RequestBody QuestionDto.Post questionPost) {

        questionPost.setTagBody(Arrays.stream(questionPost.getTagBody().split(","))
                .map(a -> Arrays.stream(a.trim()
                                .split(" "))
                        .flatMap(b -> Arrays.stream(b.split(", "))))
                .flatMap(a -> a)
                .distinct()
                .filter(a -> !Objects.equals(a, ""))
                .map(String::toLowerCase)
                .collect(Collectors.joining(", ")));

        Question question = mapper.questionPostToQuestion(questionPost);
        Question createQuestion = questionService.createQuestion(question);
        QuestionDto.Response response = mapper.questionToQuestionResponse(createQuestion);

        return new ResponseEntity<>(
                new SingleResponseDto<>(response), HttpStatus.CREATED
        );
    }

    @Secured("ROLE_USER")
    @PatchMapping("/{question_id}/edit")
    public ResponseEntity patchQuestion(@Valid @RequestBody QuestionDto.Patch questionPatch,
                                        @PathVariable("question_id") @Positive long questionId) {
        questionPatch.setQuestionId(questionId);

        questionPatch.setTagBody(Arrays.stream(questionPatch.getTagBody().split(","))
                .map(a -> Arrays.stream(a.trim()
                                .split(" "))
                        .flatMap(b -> Arrays.stream(b.split(", "))))
                .flatMap(a -> a)
                .distinct()
                .filter(a -> !Objects.equals(a, ""))
                .map(String::toLowerCase)
                .collect(Collectors.joining(", ")));

        Question question = mapper.questionPatchToQuestion(questionPatch); // 수정
        Question updateQuestion = questionService.updateQuestion(question); // DB 업데이트
        QuestionDto.Response response = mapper.questionToQuestionResponse(updateQuestion);

        return new ResponseEntity<>(
                new SingleResponseDto<>(response), HttpStatus.OK
        );
    }

    @GetMapping("/{question_id}")
    public ResponseEntity getQuestion(@PathVariable("question_id") @Positive long questionId) {
        Question question = questionService.findQuestion(questionId);
        QuestionDto.Response response = mapper.questionToQuestionResponse(question);

        return new ResponseEntity<>(
                new SingleResponseDto<>(response), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity getQuestions(@Positive @RequestParam int page,
                                       @Positive @RequestParam(required = false, defaultValue = "15") int size) {
        Page<Question> pageQuestions = questionService.findQuestions(page - 1, size);
        List<Question> questions = pageQuestions.getContent();
        List<QuestionDto.Response> responses = mapper.questionsToQuestionResponses(questions);

        return new ResponseEntity<>(
                new MultiResponseDto<>(responses, pageQuestions), HttpStatus.OK
        );
    }

//    @GetMapping("/search")
//    public ResponseEntity getQuestionsByTag(@Positive @RequestParam int page,
//                                            @Positive @RequestParam(required = false, defaultValue = "15") int size) {
//        Page<Question> pageQuestions = questionService.findQuestionsByTagBody(page - 1, size);
//        List<Question> questions = pageQuestions.getContent();
//        List<QuestionDto.Response> responses = mapper.questionsToQuestionResponses(questions);
//
//        return new ResponseEntity<>(
//                new MultiResponseDto<>(responses, pageQuestions), HttpStatus.OK
//        );
//    }


    @GetMapping("/search/{tagBody}")
    public ResponseEntity getQuestionByTagBody(@PathVariable("tagBody") @NotBlank String tagBody,
                                               @Positive @RequestParam int page,
                                               @Positive @RequestParam(required = false, defaultValue = "15") int size) {
        Page<Question> pageQuestions = questionService.findAllByTagBody(tagBody, page - 1, size);
        List<Question> questions = pageQuestions.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.questionsToQuestionResponses(questions), pageQuestions),
                HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @PatchMapping("/{question_id}")
    public ResponseEntity updateViewQuestion(@RequestBody QuestionDto.View questionView,
                                             @PathVariable("question_id") @Positive long questionId) {
        Question question = mapper.questionViewToQuestion(questionView);
        Question viewedQuestion = questionService.updateView(question, questionId);
        QuestionDto.Response response = mapper.questionToQuestionResponse(viewedQuestion);

        return new ResponseEntity<>(
                new SingleResponseDto<>(response), HttpStatus.OK
        );
    }

    @PatchMapping("/{question-id}/vote") // Question Vote
    public ResponseEntity voteQuestion(@PathVariable("question-id") @Positive long questionId,
                                       @Valid @RequestBody QuestionDto.Vote questionVote) {
        questionVoteService.postVote(questionId);

        Question question = questionService.updateVote(mapper.questionVoteToQuestion(questionVote), questionId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.questionToQuestionResponse(question)), HttpStatus.OK
        );
    }

    @Secured("ROLE_USER")
    @DeleteMapping("/{question_id}/delete")
    public ResponseEntity deleteQuestion(@PathVariable("question_id") @Positive long questionId) {
        questionService.deleteQuestion(questionId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 답변 채택
    @PatchMapping("/{question-id}/favorite/{answer-id}")
    public ResponseEntity favoriteAnswer(@PathVariable("question-id") @Positive long questionId,
                                         @PathVariable("answer-id") @Positive long answerId,
                                         @Valid @RequestBody AnswerBestDto answerBestDto) {
        answerBestDto.setAnswerId(answerId);

        Question postQuestion = questionService.findQuestion(questionId);
        Answer answer = answerService.updateStatus(answerMapper.answerBestDtoToAnswer(answerBestDto), postQuestion);

        return new ResponseEntity<>(
                new SingleResponseDto<>(answerMapper.answerToAnswerResponseDto(answer)),
                HttpStatus.OK);
    }

    @GetMapping("/topquestions/{tab}")
    public ResponseEntity getTopQuestions(@PathVariable("tab") @NotBlank String tab,
                                          @Positive @RequestParam(required = false, defaultValue = "1") int page,
                                          @Positive @RequestParam(required = false, defaultValue = "100") int size) {
        if(!tab.equals("hot") && !tab.equals("week") && !tab.equals("month"))
            return new ResponseEntity<>("잘못된 tab 요청입니다", HttpStatus.BAD_REQUEST);

        Page<Question> pageQuestions = questionService.findTopQuestions(tab, page - 1, size);
        List<Question> questions = pageQuestions.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.questionsToQuestionResponsesTopAll(questions), pageQuestions),
                HttpStatus.OK);
    }

    @GetMapping(value = {"/allquestions", "/allquestions/{tab}"})
    public ResponseEntity getAllQuestions(@PathVariable(required = false, value = "tab") String tab,
                                          @Positive @RequestParam int page,
                                          @Positive @RequestParam(required = false, defaultValue = "15") int size) {
        if(tab == null) tab = "newest";
        if(!tab.equals("newest") && !tab.equals("score") && !tab.equals("unanswered"))
            return new ResponseEntity<>("잘못된 tab 요청입니다", HttpStatus.BAD_REQUEST);

        Page<Question> pageQuestions = questionService.findAllQuestions(tab, page - 1, size);
        List<Question> questions = pageQuestions.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.questionsToQuestionResponsesTopAll(questions), pageQuestions),
                HttpStatus.OK);
    }
}
