package com.codestates.comment;

import com.codestates.comment.entity.CommentType;
import com.codestates.member.dto.MemberDto;
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

        private long memberId;
        @NotBlank(message = "공백이 아니어야 합니다.")
        private String body;
//        private CommentType commentType;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Patch {
        private long commentId;
        private long memberId;
        @NotBlank(message = "공백이 아니어야 합니다.")
        private String body;
    }

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response {
        private long commentId;
        private long postId;
        private MemberDto.Response memberResponseDto;
//        private long memberId;
        private String body;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private CommentType commentType;

//        public Response(long commentId, long postId, long memberId, String body,
//                        LocalDateTime createdAt, LocalDateTime modifiedAt,
//                        CommentType commentType) {
//            this.commentId = commentId;
//            this.postId = getPostId();
//            this.memberId = memberId;
//            this.body = body;
//            this.createdAt = createdAt;
//            this.modifiedAt = modifiedAt;
//            this.commentType = commentType;
//        }
    }
}
