package com.codestates.question;

import com.codestates.comment.CommentDto;
import com.codestates.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public class QuestionDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Post {

        /*@Positive
        private long userId;*/

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
        private int answerCount;
        private List<CommentDto.Response> commentsWithUser;
        private String actionStatus;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Vote {
        private long questionId;
        private int vote;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class View {
        private long questionId;
        private int view;
    }

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    public static class ResponseTopAll { // Top Questions와 All Questions에서 사용되는 Response
        private long questionId;
        private String title;
        private String problem;
        private String expect;
        private List<String> tagList;
        private int view;
        private int vote;
        private int answerCount;
        private boolean accepted; // 채택된 답변 여부
        private String status; // asked, answered, modified
        private UserDto.Response actionUser; // asked, answered, modified에 대한 유저
        private LocalDateTime actionTime; // asked, answered, modified에 대한 시간
    }
}
