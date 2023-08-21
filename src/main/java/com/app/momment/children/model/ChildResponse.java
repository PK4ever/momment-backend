package com.app.momment.children.model;

import java.util.Date;

public class ChildResponse {

    long id;
    String  name;
    Date dob;

    public ChildResponse(long id, String name, Date dob) {
        this.id = id;
        this.name = name;
        this.dob = dob;
    }
}
