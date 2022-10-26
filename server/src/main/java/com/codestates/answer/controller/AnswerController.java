package com.codestates.answer.controller;

import com.codestates.answer.dto.AnswerPatchDto;
import com.codestates.answer.dto.AnswerPostDto;
import com.codestates.answer.entity.Answer;
import com.codestates.answer.mapper.AnswerMapper;
import com.codestates.answer.service.AnswerService;
import com.codestates.dto.MultiAnsResponseDto;
import com.codestates.dto.SingleResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/answer")
@Validated
public class AnswerController {
    private final AnswerService answerService;
    private final AnswerMapper mapper;

    public AnswerController(AnswerService answerService, AnswerMapper mapper) {
        this.answerService = answerService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postAnswer(@Valid @RequestBody AnswerPostDto answerPostDto) {
        Answer answer = answerService.createAnswer(mapper.answerPostDtoToAnswer(answerPostDto));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.answerToAnswerResponseDto(answer)),
                HttpStatus.CREATED);
    }

    @PatchMapping("/{answer-id}")
    public ResponseEntity patchAnswer(@PathVariable("answer-id") @Positive long answerId,
                                      @Valid @RequestBody AnswerPatchDto answerPatchDto) {
        answerPatchDto.setAnswerId(answerId);

        Answer answer = answerService.updateAnswer(mapper.answerPatchDtoToAnswer(answerPatchDto));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.answerToAnswerResponseDto(answer)),
                HttpStatus.OK);
    }

    @GetMapping("/{answer-id}")
    public ResponseEntity getAnswer(@PathVariable("answer-id") @Positive long answerId) {
        Answer answer = answerService.findAnswer(answerId);

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.answerToAnswerResponseDto(answer)),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAnswers(@Positive @RequestParam int size) {
        List<Answer> answers = answerService.findAnswers(size);

        // TODO: Answer Service 완성

        return new ResponseEntity<>(
                new MultiAnsResponseDto<>(mapper.answersToAnswerResponseDtos(answers)),
                HttpStatus.OK);
    }

    @DeleteMapping("/{answer-id}")
    public ResponseEntity deleteAnswer(@PathVariable("answer-id") @Positive long answerId) {
        answerService.deleteAnswer(answerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
