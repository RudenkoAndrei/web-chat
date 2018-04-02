package ua.com.webchat.chatik.data.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Rudenko Andrey
 * @project chatik
 */
public class UserDTO {

    @NotNull
    @Size(min = 1)
    private String username;

    private String password;

    @NotNull
    @Size(min = 1)
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
