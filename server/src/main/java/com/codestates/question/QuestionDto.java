package com.codestates.question;

import com.codestates.answer.entity.Answer;
import com.codestates.reply.entity.Reply;
import com.codestates.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

public class QuestionDto {

    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotBlank(message = "제목은 공백이 아니어야 합니다.")
        private String title;
        @NotEmpty(message = "내용을 입력하셔야 합니다.")
        private String body;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private long questionId;
        @NotBlank(message = "제목은 공백이 아니어야 합니다.")
        private String title;
        @NotEmpty(message = "내용을 입력하셔야 합니다.")
        private String body;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private long questionId;
        private long memberId;
        private String title;
        private String body;
        private int vote;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private List<Answer> answers;
        private List<Reply> replies;
        private List<Tag> tags;
        /*멤버&답변&댓글 추가할 것!*/
    }
}
