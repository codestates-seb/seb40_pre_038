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
        Question createQuestion = questionService.createQuestion(question, questionPost.getUserId());
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
        Question updateQuestion = questionService.updateQuestion(question, questionPatch.getUserId()); // DB 업데이트
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

//    @GetMapping("/search/{tagBody}")
//    public ResponseEntity getListsByTag(@PathVariable("tagBody") String tagBody) {
//        List<Integer> pageQuestions = questionService.findListByTagBody(tagBody);
//
//        return new ResponseEntity<>(
//                new SingleResponseDto<>(pageQuestions), HttpStatus.OK
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

//    @GetMapping("/search/{tagBody}")
//    public ResponseEntity getQuestionsByTag(@PathVariable("tagBody") String tagBody,
//                                            @Positive @RequestParam int page,
//                                            @Positive @RequestParam(required = false, defaultValue = "15") int size) {
//        Page<Question> pageQuestions = questionService.findQuestionsByTagBody(tagBody,page - 1, size);
//        List<Question> questions = pageQuestions.getContent();
//        List<QuestionDto.Response> responses = mapper.questionsToQuestionResponses(questions);
//
//        return new ResponseEntity<>(
//                new MultiResponseDto<>(responses, pageQuestions), HttpStatus.OK
//        );
//    }

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
        questionVoteService.postVote(questionId, questionVote.getUserId());

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
    @PatchMapping("/{question_id}/favorite/{answer-id}")
    public ResponseEntity favoriteAnswer(@PathVariable("answer-id") @Positive long answerId,
                                         @Valid @RequestBody AnswerBestDto answerBestDto) {
        answerBestDto.setAnswerId(answerId);

        Answer answer = answerService.updateStatus(answerMapper.answerBestDtoToAnswer(answerBestDto));

        return new ResponseEntity<>(
                new SingleResponseDto<>(answerMapper.answerToAnswerResponseDto(answer)),
                HttpStatus.OK);
    }

    /*@Secured("ROLE_USER")
    @PatchMapping("/{question_id}/upvote")
    public ResponseEntity upVoteQuestion(@RequestBody QuestionDto.Vote questionVote,
                                         @PathVariable("question_id") @Positive long questionId) {
        Question question = mapper.questionVoteToQuestion(questionVote);
        Question votedQuestion = questionService.upVote(question, questionId);
        QuestionDto.Response response = mapper.questionToQuestionResponse(votedQuestion);

        return new ResponseEntity<>(
                new SingleResponseDto<>(response), HttpStatus.OK
        );
    }

    @Secured("ROLE_USER")
    @PatchMapping("/{question_id}/downvote")
    public ResponseEntity downVoteQuestion(@RequestBody QuestionDto.Vote questionVote,
                                           @PathVariable("question_id") @Positive long questionId) {
        Question question = mapper.questionVoteToQuestion(questionVote);
        Question votedQuestion = questionService.downVote(question, questionId);
        QuestionDto.Response response = mapper.questionToQuestionResponse(votedQuestion);

        return new ResponseEntity<>(
                new SingleResponseDto<>(response), HttpStatus.OK
        );
    }
    */
}
