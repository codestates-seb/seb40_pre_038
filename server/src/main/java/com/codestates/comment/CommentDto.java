package com.codestates.comment;

import com.codestates.comment.entity.CommentType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class CommentDto {

    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotBlank(message = "공백이 아니어야 합니다.")
        private long postId;
        private String body;
//        private CommentType commentType;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long commentId;
        @NotBlank(message = "공백이 아니어야 합니다.")
        private String body;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private long commentId;
        private String body;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private long memberId;
        private CommentType commentType;
        private long postId;
    }
}
