package com.codestates.question;

import com.codestates.answer.entity.Answer;
import com.codestates.comment.entity.Comment;
import com.codestates.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

public class QuestionDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Post {
        @Positive
        private long userId;
        @NotBlank(message = "제목은 공백이 아니어야 합니다.")
        private String title;
        @NotEmpty(message = "내용을 입력하셔야 합니다.")
        private String body;
        private String tagBody;
        private List<String> tags;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Patch {
        private long questionId;
        private long userId;
        @NotBlank(message = "제목은 공백이 아니어야 합니다.")
        private String title;
        @NotEmpty(message = "내용을 입력하셔야 합니다.")
        private String body;
        private String tagBody;
        private List<String> tags;
    }

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response {
        private long questionId;
        private String title;
        private String body;
        private List<String> tagList;
        private int view;
        private int vote;
//        private long userId;
//        private String nickName;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private List<Answer> answers;
        private List<Comment> comments;
//        private Set<QuestionTag> tags;
        /*멤버&답변&댓글 추가할 것!*/
//        private TagDto.Response tagResponseDto;
//        private List<TagDto.Response> tags;
        private UserDto.Response userResponseDto;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Vote {
        private long questionId;
        @Positive
        private long userId;
        private int vote;
//        private String tag;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class View{
        private long questionId;
        private int view;
    }
}
