package com.app.momment.children;

import com.app.momment.users.LoginRequest;
import com.app.momment.users.User;

import java.util.Date;

public class ChildRequest {
    long id;
    String name;
    Date dob;

    String userEmail;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDob() {
        return dob;
    }
}
