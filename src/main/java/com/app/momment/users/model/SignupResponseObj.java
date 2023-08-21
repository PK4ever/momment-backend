package com.app.momment.users.model;

public class SignupResponseObj {
    private String name;
    private String email;

    private boolean  isEnabled;

    public String getName() {
        return name;
    }

    public SignupResponseObj(String name, String email, boolean isEnabled) {
        this.name = name;
        this.email = email;
        this.isEnabled = isEnabled;
    }

    public boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
