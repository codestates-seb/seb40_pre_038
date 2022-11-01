package com.codestates.answer.dto;

import com.codestates.answer.entity.Answer;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
public class AnswerBestDto {
    private long answerId;

    @Enumerated(EnumType.ORDINAL)
    private Answer.AnswerStatus answerStatus;

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }
}
