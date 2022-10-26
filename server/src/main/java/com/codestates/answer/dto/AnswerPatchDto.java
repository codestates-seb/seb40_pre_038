package com.codestates.answer.dto;

import com.codestates.answer.entity.Answer;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
public class AnswerPatchDto {
    private long answerId;

    @NotBlank(message = "공백이 아니어야 합니다.")
    private String contents;

    /*@Positive
    private long memberId;*/

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }
}
