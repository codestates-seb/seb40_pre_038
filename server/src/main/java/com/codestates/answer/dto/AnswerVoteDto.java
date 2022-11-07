package com.codestates.answer.dto;

import lombok.Getter;

@Getter
public class AnswerVoteDto {
    private long answerId;

    private int vote;

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }
}
