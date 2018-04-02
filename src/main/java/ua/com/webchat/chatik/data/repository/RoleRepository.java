package ua.com.webchat.chatik.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.webchat.chatik.data.entity.Role;

/**
 * @author Rudenko Andrey
 * @project chatik
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);
}
