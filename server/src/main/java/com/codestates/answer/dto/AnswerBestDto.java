package com.codestates.answer.dto;

import com.codestates.answer.entity.Answer;
import lombok.Getter;

@Getter
public class AnswerBestDto {
    private long answerId;

    private Answer.AnswerStatus answerStatus;

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }
}
