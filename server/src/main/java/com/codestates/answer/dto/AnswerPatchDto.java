package com.codestates.answer.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class AnswerPatchDto {
    private long answerId;

    @NotBlank(message = "공백이 아니어야 합니다.")
    private String body;

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }
}
