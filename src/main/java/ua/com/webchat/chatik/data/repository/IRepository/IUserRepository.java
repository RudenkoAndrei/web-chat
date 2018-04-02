package ua.com.webchat.chatik.data.repository.IRepository;

import ua.com.webchat.chatik.data.dto.UserDTO;
import ua.com.webchat.chatik.data.entity.User;
import ua.com.webchat.chatik.data.entity.VerificationToken;

/**
 * @author Rudenko Andrey
 * @project chatik
 */
public interface IUserRepository {

    User registerUserAccount(UserDTO accountDTO);

    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String verificationToken);

}
