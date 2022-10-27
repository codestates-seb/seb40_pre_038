package com.codestates.question;

import com.codestates.answer.dto.AnswerBestDto;
import com.codestates.answer.entity.Answer;
import com.codestates.answer.mapper.AnswerMapper;
import com.codestates.answer.service.AnswerService;
import com.codestates.dto.MultiResponseDto;
import com.codestates.dto.SingleResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@RestController
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionMapper mapper;
    private final AnswerService answerService; // 답변 채택 기능에서 필요
    private final AnswerMapper answerMapper;

    public QuestionController(QuestionService questionService, QuestionMapper mapper,
                              AnswerService answerService, AnswerMapper answerMapper) {
        this.questionService = questionService;
        this.mapper = mapper;
        this.answerService = answerService;
        this.answerMapper = answerMapper;
    }

    @Secured("ROLE_USER")
    @PostMapping("/add")
    public ResponseEntity postQuestion(@Valid @RequestBody QuestionDto.Post questionPost) {
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
        Question question = mapper.questionPatchToQuestion(questionPatch); // 수정
        Question updateQuestion = questionService.updateQuestion(question, questionId); // DB 업데이트
        QuestionDto.Response response = mapper.questionToQuestionResponse(updateQuestion);

        return new ResponseEntity<>(
                new SingleResponseDto<>(response), HttpStatus.OK
        );
    }

    @Secured("ROLE_USER")
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

    @Secured("ROLE_USER")
    @DeleteMapping("/{question_id}")
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
}
