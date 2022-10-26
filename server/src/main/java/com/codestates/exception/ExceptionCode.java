package com.codestates.exception;

import lombok.Getter;

public enum ExceptionCode {
    QUESTION_NOT_FOUND(404,"Question Not Found"),
    QUESTION_EXISTS(409, "Question Exists"),
    QUESTION_CANNOT_CHANGE(403,"Question Can Not Be Changed"),

    REPLY_NOT_FOUND(404,"Reply Not Found"),
    REPLY_EXISTS(409, "Reply Exists"),
    REPLY_CANNOT_CHANGE(403,"Reply Can Not Be Changed"),

    ANSWER_NOT_FOUND(404,"Answer Not Found"),
    ANSWER_EXISTS(409, "Answer Exists"),
    ANSWER_CANNOT_CHANGE(403,"Answer Can Not Be Changed"),
    ANSWER_DELETED(405, "Answer Already Deleted"),

    MEMBER_NOT_FOUND(404,"Member Not Found"),
    MEMBER_EXISTS(409, "Member Exists"),
    MEMBER_CANNOT_CHANGE(403,"Member Can Not Be Changed");

    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode(int status, String message){
        this.status = status;
        this.message = message;
    }
}
