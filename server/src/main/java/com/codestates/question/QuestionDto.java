package com.codestates.question;

import com.codestates.answer.entity.Answer;
import com.codestates.comment.entity.Comment;


import com.codestates.member.entity.Member;
import com.codestates.member.dto.MemberDto;
import com.codestates.tag.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class QuestionDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Post {
        @Positive
        private long memberId;
        @NotBlank(message = "제목은 공백이 아니어야 합니다.")
        private String title;
        @NotEmpty(message = "내용을 입력하셔야 합니다.")
        private String body;
        private List<Tag> tags;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Patch {
        private long questionId;
        private long memberId;
        @NotBlank(message = "제목은 공백이 아니어야 합니다.")
        private String title;
        @NotEmpty(message = "내용을 입력하셔야 합니다.")
        private String body;
    }

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response {
        private long questionId;
        private String title;
        private String body;
        private int view;
        private int vote;
//        private long memberId;
//        private String nickName;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private List<Answer> answers;
        private List<Comment> comments;
<<<<<<< HEAD
        private Set<QuestionTag> tags;
=======
        private List<Tag> tags;
>>>>>>> dev
        /*멤버&답변&댓글 추가할 것!*/
        private MemberDto.Response memberResponseDto;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Vote {
        private long questionId;
        @Positive
        private long memberId;
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
