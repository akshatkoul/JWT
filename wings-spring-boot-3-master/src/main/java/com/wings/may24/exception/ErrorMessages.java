package com.wings.may24.exception;

public enum ErrorMessages {

    ROLE_NOT_FOUND("No role is found."),
    USER_ALREADY_EXISTS("User already exists."),
    USER_NOT_FOUND("User is not found.");

    private String errorMessage;
    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
