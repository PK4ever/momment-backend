package com.app.momment.highlight;

import com.app.momment.children.Child;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="highlight")
public class Highlight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

//    @OneToOne()
//    @ForeignKey
    @ManyToOne
    @JoinColumn(name="child_id", nullable=false, referencedColumnName = "id")
    private Child child;

    @Column
    private Date date;

    @Column(nullable = false)
    private String description;

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return this.date;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }
}
