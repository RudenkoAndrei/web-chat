package ua.com.webchat.chatik.data.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * @author Rudenko Andrey
 * @project chatik
 */
@Entity
@Table(name = "verificationtoken")
public class VerificationToken {

    private static final int EXPIRATION = 60*24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;

    private String token;

    @OneToOne
    @JoinColumn(nullable = false, name = "user_id", foreignKey = @ForeignKey(name = "user_id_reference"))
    private User user;

    private Date expiryDate;

    public VerificationToken(){}

    public VerificationToken(User user, String token){
        this.user = user;
        this.token = token;
    }

    private Date calculateExpiryDate(int expiryTimeInMinutes){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTime().getTime()));
        calendar.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(calendar.getTime().getTime());
    }

    public int getExpiration(){
        return EXPIRATION;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        this.expiryDate = calculateExpiryDate(1440);
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

}
