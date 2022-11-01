package com.codestates.answer.dto;

import lombok.Getter;

import javax.validation.constraints.Positive;

@Getter
public class AnswerVoteDto {
    private long answerId;

    @Positive
    private long userId;

    private int vote;

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }
}
