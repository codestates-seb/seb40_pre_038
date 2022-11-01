package com.codestates.answer.dto;

import com.codestates.answer.entity.Answer;
import com.codestates.comment.entity.Comment;
import com.codestates.user.dto.UserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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

    private List<Comment> comments;

    private UserDto.Response userResponseDto;
}
