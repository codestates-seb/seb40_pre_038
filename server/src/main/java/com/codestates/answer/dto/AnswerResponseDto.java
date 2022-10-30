package com.codestates.answer.dto;

import com.codestates.answer.entity.Answer;
import com.codestates.member.dto.MemberDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
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

    private MemberDto.Response memberResponseDto;
}
