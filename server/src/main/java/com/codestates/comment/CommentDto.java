package com.codestates.comment;

import com.codestates.comment.entity.CommentType;
import com.codestates.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class CommentDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Post {

        private long userId;
        @NotBlank(message = "공백이 아니어야 합니다.")
        private String body;
//        private CommentType commentType;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Patch {
        private long commentId;
        private long userId;
        @NotBlank(message = "공백이 아니어야 합니다.")
        private String body;
    }

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response {
        private long commentId;
//        private long postId;
        private UserDto.Response userResponseDto;
//        private long userId;
        private String body;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private CommentType commentType;

//        public Response(long commentId, long postId, long userId, String body,
//                        LocalDateTime createdAt, LocalDateTime modifiedAt,
//                        CommentType commentType) {
//            this.commentId = commentId;
//            this.postId = getPostId();
//            this.userId = userId;
//            this.body = body;
//            this.createdAt = createdAt;
//            this.modifiedAt = modifiedAt;
//            this.commentType = commentType;
//        }
    }
}
