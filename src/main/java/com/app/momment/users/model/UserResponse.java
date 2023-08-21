package com.app.momment.users.model;

public class UserResponse {
    private long id;
    private String name;
    private String email;

    private boolean isEnabled;

    public UserResponse(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
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
