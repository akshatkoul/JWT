package com.wings.may24.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(){
        super(ErrorMessages.USER_ALREADY_EXISTS.toString());
    }
}
