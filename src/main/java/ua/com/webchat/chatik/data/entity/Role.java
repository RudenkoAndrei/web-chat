package ua.com.webchat.chatik.data.entity;

import javax.persistence.*;

/**
 * @author Rudenko Andrey
 * @project chatik
 */
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "role")
    private String role;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "fk_user_role"))
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
