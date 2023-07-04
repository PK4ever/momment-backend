package com.app.momment.children;

import com.app.momment.users.User;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String name;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column
    private Date dob;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false, referencedColumnName = "id")
    private User user;
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getDob() {
        return this.dob;
    }
}
