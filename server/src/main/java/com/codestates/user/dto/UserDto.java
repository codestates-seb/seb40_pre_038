package com.codestates.user.dto;

import com.codestates.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserDto {

    @Getter
    @AllArgsConstructor
    public static class Post {

        @NotBlank(message = "NickName cannot be empty")
        private String nickName;

        @NotBlank(message = "Email cannot be empty.")
        @Email
        private String email;

        @NotBlank
        private String password;

    }

    @Getter
    @AllArgsConstructor
    public static class Patch {

        private Long userId;

        @NotBlank(message = "NickName cannot be empty.")
        private String nickName;

        @NotBlank(message = "Email cannot be empty.")
        @Email
        private String email;

        @NotBlank
        private String password;

        private User.UserStatus userStatus;

        public void setUserId(Long userId) {
            this.userId = userId;
        }
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class Response {
        private long userId;
        private String nickName;
        private String email;
        private int reputation;
        private User.UserStatus userStatus;
    }
}
