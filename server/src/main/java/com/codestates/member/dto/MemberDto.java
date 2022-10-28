package com.codestates.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


// Not used yet

public class MemberDto {

    @Getter
    @AllArgsConstructor
    public static class Post {

        private String nickName;  // "Display name": Optional

        @NotBlank(message = "Email cannot be empty.")
        @Email
        private String email;

        @NotBlank
        private String password;

    }

    @Getter
    @Setter
    public static class Response {
        private long memberId;
        private String nickName;
        private String email;
    }
}
