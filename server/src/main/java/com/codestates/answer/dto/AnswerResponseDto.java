package com.codestates.answer.dto;

import com.codestates.answer.entity.Answer;
import com.codestates.member.dto.MemberResponseDto;
import com.codestates.question.Question;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AnswerResponseDto {
    private long answerId;
    private Answer.AnswerStatus answerStatus;

    private long questionId;

    private String body;
    private int vote;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private MemberResponseDto memberResponseDto;
}
