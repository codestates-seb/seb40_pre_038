package com.codestates.answer.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
public class AnswerPostDto {
    @NotBlank
    private String body;

    @Positive
    private long memberId;

    @Positive
    private long questionId;
}
