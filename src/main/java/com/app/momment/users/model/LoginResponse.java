package com.app.momment.users.model;

public class LoginResponse {

    private UserResponse user;
    public String message;
    public boolean status;

    public LoginResponse(String message, boolean status, UserResponse user) {
        this.message = message;
        this.status = status;
        this.user = user;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
