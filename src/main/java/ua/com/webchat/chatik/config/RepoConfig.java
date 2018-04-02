package ua.com.webchat.chatik.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Rudenko Andrey
 * @project chatik
 */

@Configuration
@EnableJpaRepositories(basePackages = "ua.com.webchat.chatik.data.repository")
@EntityScan(basePackages = "ua.com.webchat.chatik.data.entity")
@EnableTransactionManagement
public class RepoConfig {
}
