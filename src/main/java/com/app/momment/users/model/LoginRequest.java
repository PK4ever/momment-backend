package com.app.momment.users.model;

public class LoginRequest {
    private String email;
    private String Password;

    public LoginRequest() {
    }

    public LoginRequest(String email, String password) {
        this.email = email;
        Password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
