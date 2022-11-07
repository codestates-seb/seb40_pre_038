package com.codestates.comment;

import com.codestates.comment.entity.CommentType;
import com.codestates.user.dto.UserDto;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class CommentDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {

        @NotBlank(message = "공백이 아니어야 합니다.")
        private String body;

        public Post(String body) {
            this.body = body;
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Patch {
        private long commentId;

        @NotBlank(message = "공백이 아니어야 합니다.")
        private String body;
    }

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response {
        private long commentId;
        private UserDto.Response userInformation;
        private String body;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private CommentType commentType;
    }
}
