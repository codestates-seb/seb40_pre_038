package com.codestates.auth.utils;

import com.codestates.exception.user.entity.User;
import lombok.Getter;

@Getter
public class UserRegistrationApplicationEvent {
    private User user;
    public UserRegistrationApplicationEvent(User user) {
        this.user = user;
    }
}
