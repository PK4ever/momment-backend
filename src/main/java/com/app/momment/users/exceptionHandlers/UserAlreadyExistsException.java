package com.app.momment.users.exceptionHandlers;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException(String  errorMsg) {
        super(errorMsg);
    }
}
