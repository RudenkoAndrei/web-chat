package ua.com.webchat.chatik.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.webchat.chatik.data.entity.VerificationToken;

/**
 * @author Rudenko Andrey
 * @project chatik
 */
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {
    VerificationToken findByToken(String token);
}
