package com.app.momment.children.model;

import com.app.momment.highlight.model.Highlight;
import com.app.momment.users.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.Date;
import java.util.List;

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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false, referencedColumnName = "id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL)
    private List<Highlight> highlightList;
    public long getId() {
        return id;
    }

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
