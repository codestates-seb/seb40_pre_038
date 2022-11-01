package com.codestates.question;

import com.codestates.comment.entity.Comment;
import com.codestates.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
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
        @Size(min = 20, message = "20자 이상 입력하셔야 합니다.")
        private String problem;

        @NotEmpty(message = "내용을 입력하셔야 합니다.")
        @Size(min = 20, message = "20자 이상 입력하셔야 합니다.")
        private String expect;

        @NotEmpty(message = "내용을 입력하셔야 합니다.")
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
        @Size(min = 20, message = "20자 이상 입력하셔야 합니다.")
        private String problem;

        @NotEmpty(message = "내용을 입력하셔야 합니다.")
        @Size(min = 20, message = "20자 이상 입력하셔야 합니다.")
        private String expect;

        @NotEmpty(message = "내용을 입력하셔야 합니다.")
        private String tagBody;

        private List<String> tags;
    }

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response {
        private long questionId;
        private UserDto.Response userInformation;
        private String title;
        private String problem;
        private String expect;
        private List<String> tagList;
        private int view;
        private int vote;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private List<Comment> comments;
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
    public static class View {
        private long questionId;
        private int view;
    }
}
