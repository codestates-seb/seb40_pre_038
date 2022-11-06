package com.codestates.answer.controller;

import com.codestates.answer.dto.AnswerPatchDto;
import com.codestates.answer.dto.AnswerPostDto;
import com.codestates.answer.dto.AnswerVoteDto;
import com.codestates.answer.entity.Answer;
import com.codestates.answer.mapper.AnswerMapper;
import com.codestates.answer.service.AnswerService;
import com.codestates.dto.MultiAnsResponseDto;
import com.codestates.dto.SingleResponseDto;
import com.codestates.vote.AnswerVote.AnswerVoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

//@CrossOrigin
@RestController
@Validated
@RequestMapping("/api")
public class AnswerController {
    private final AnswerService answerService;
    private final AnswerMapper mapper;
    private final AnswerVoteService answerVoteService;

    public AnswerController(AnswerService answerService, AnswerMapper mapper, AnswerVoteService answerVoteService) {
        this.answerService = answerService;
        this.mapper = mapper;
        this.answerVoteService = answerVoteService;
    }

    @PostMapping("/questions/{question-id}/answers/add") // Answer 생성
    public ResponseEntity postAnswer(@PathVariable("question-id") @Positive long questionId,
                                     @Valid @RequestBody AnswerPostDto answerPostDto) {
        Answer answer = answerService.createAnswer(mapper.answerPostDtoToAnswer(questionId, answerPostDto));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.answerToAnswerResponseDto(answer)),
                HttpStatus.CREATED);
    }

    @PatchMapping("/answers/{answer-id}/edit") // Answer 편집
    public ResponseEntity patchAnswer(@PathVariable("answer-id") @Positive long answerId,
                                      @Valid @RequestBody AnswerPatchDto answerPatchDto) {
        answerPatchDto.setAnswerId(answerId);

        Answer answer = answerService.updateAnswer(mapper.answerPatchDtoToAnswer(answerPatchDto));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.answerToAnswerResponseDto(answer)),
                HttpStatus.OK);
    }

    @PatchMapping("/answers/{answer-id}/vote") // Answer Vote
    public ResponseEntity voteAnswer(@PathVariable("answer-id") @Positive long answerId,
                                     @Valid @RequestBody AnswerVoteDto answerVoteDto) {
        answerVoteService.postVote(answerId);

        answerVoteDto.setAnswerId(answerId);
        Answer answer = answerService.updateVote(mapper.answerVoteDtoToAnswer(answerVoteDto));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.answerToAnswerResponseDto(answer)),
                HttpStatus.OK);
    }

    /*@PatchMapping("/{answer-id}/best") // QuestionController에서 동작
    public ResponseEntity patchStatus(@PathVariable("answer-id") @Positive long answerId,
                                     @Valid @RequestBody AnswerBestDto answerBestDto) {
        answerBestDto.setAnswerId(answerId);

        Answer answer = answerService.updateStatus(mapper.answerBestDtoToAnswer(answerBestDto));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.answerToAnswerResponseDto(answer)),
                HttpStatus.OK);
    }*/

    @GetMapping("/questions/{question-id}/answers")
    public ResponseEntity getAnswers(@PathVariable("question-id") @Positive long questionId) {
        List<Answer> answers = answerService.findAnswers(questionId);

        return new ResponseEntity<>(
                new MultiAnsResponseDto<>(mapper.answersToAnswerResponseDtos(answers)),
                HttpStatus.OK);
    }

    @DeleteMapping("/answers/{answer-id}/delete")
    public ResponseEntity deleteAnswer(@PathVariable("answer-id") @Positive long answerId) {
        answerService.deleteAnswer(answerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
