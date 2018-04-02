package ua.com.webchat.chatik.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.webchat.chatik.data.entity.User;
import ua.com.webchat.chatik.data.repository.IRepository.IUserRepository;

/**
 * @author Rudenko Andrey
 * @project chatik
 */
public interface UserRepository extends JpaRepository<User, Integer>, IUserRepository {
    User findUserByEmail(String email);
    User findUserByUsername(String username);
}
