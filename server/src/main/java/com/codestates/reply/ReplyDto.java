package com.codestates.reply;

import com.codestates.reply.entity.ReplyType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class ReplyDto {

    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotBlank(message = "공백이 아니어야 합니다.")
        private String body;
        private ReplyType replyType;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long replyId;
        @NotBlank(message = "공백이 아니어야 합니다.")
        private String body;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private long replyId;
        private String body;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private long memberId;
        private ReplyType replyType;
        private long postId;
    }
}
