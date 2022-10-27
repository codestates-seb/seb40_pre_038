package com.codestates.auth.dto;

import lombok.Getter;

@Getter
public class LoginDto {

    // email vs nickName ?
    private String username;
    private String password;
}
