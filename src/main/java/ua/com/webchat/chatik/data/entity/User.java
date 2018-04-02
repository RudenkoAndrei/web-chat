package ua.com.webchat.chatik.data.entity;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

/**
 * @author Rudenko Andrey
 * @project chatik
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @ColumnDefault("1")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    private Role role;

    public User(){
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
