package com.codestates.answer.dto;

import com.codestates.answer.entity.Answer;
import com.codestates.comment.CommentDto;
import com.codestates.comment.entity.Comment;
import com.codestates.user.dto.UserDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
public class AnswerResponseDto {
    private long answerId;
    private UserDto.Response userInformation;
    private Answer.AnswerStatus answerStatus;
    private long questionId;
    private String body;
    private int vote;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentDto.Response> commentsWithUser;
    private String actionStatus;

    public AnswerResponseDto(long answerId, UserDto.Response userInformation, Answer.AnswerStatus answerStatus,
                             long questionId, String body, int vote, LocalDateTime createdAt, LocalDateTime modifiedAt,
                             List<CommentDto.Response> commentsWithUser, String actionStatus) {
        this.answerId = answerId;
        this.userInformation = userInformation;
        this.answerStatus = answerStatus;
        this.questionId = questionId;
        this.body = body;
        this.vote = vote;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.commentsWithUser = commentsWithUser;
        this.actionStatus = actionStatus;
    }
}
