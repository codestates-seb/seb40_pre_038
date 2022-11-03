package com.codestates.answer.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
public class AnswerPatchDto {
    private long answerId;

    @NotEmpty(message = "내용을 입력하셔야 합니다.")
    @Size(min = 20, message = "20자 이상 입력하셔야 합니다.")
    private String body;

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }
}

