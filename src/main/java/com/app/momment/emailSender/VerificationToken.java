package com.app.momment.emailSender;

import com.app.momment.users.model.User;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
public class VerificationToken {
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable=false, referencedColumnName = "id")
    private User user;

    private Date expiryDate;

    private String token;

    public VerificationToken(String token, User user) {
        super();
        this.token = token;
        this.user = user;
    }

    public VerificationToken() {}

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(int minutes) {
        this.expiryDate = calculateExpiryDate(minutes);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
