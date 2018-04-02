package ua.com.webchat.chatik.data.repository.RepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ua.com.webchat.chatik.data.dto.UserDTO;
import ua.com.webchat.chatik.data.entity.Role;
import ua.com.webchat.chatik.data.entity.User;
import ua.com.webchat.chatik.data.entity.VerificationToken;
import ua.com.webchat.chatik.data.repository.IRepository.IUserRepository;
import ua.com.webchat.chatik.data.repository.RoleRepository;
import ua.com.webchat.chatik.data.repository.UserRepository;
import ua.com.webchat.chatik.data.repository.VerificationTokenRepository;

/**
 * @author Rudenko Andrey
 * @project chatik
 */

public class UserRepositoryImpl implements IUserRepository {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUserAccount(UserDTO accountDTO) {

        if(isEmailExists(accountDTO.getEmail())){
            System.out.println("There is an account with email " + accountDTO.getEmail());
            return null;
        }
        else {
            User user = new User();
            user.setUsername(accountDTO.getUsername());
            user.setEmail(accountDTO.getEmail());
            user.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
            Role role = new Role();
            role.setUser(user);
            role.setRole("ROLE_USER");
//            roleRepository.save(role);
            user.setRole(role);

            return userRepository.save(user);
        }
    }

    @Override
    public User getUser(String verificationToken) {
        return verificationTokenRepository.findByToken(verificationToken).getUser();
    }

    @Override
    public void saveRegisteredUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public VerificationToken getVerificationToken(String verificationToken) {
        return verificationTokenRepository.findByToken(verificationToken);
    }
    private boolean isEmailExists(String email){
        User user = userRepository.findUserByEmail(email);
        return user != null;
    }
}
