package ua.com.webchat.chatik.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author Rudenko Andrey
 * @project chatik
 */
@Configuration
@EnableTransactionManagement
public class UserDetailsConfig {

    private final DataSource dataSource;

    @Autowired
    public UserDetailsConfig(@Qualifier("dataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean("userDetailsService")
    public UserDetailsService userDetailsService(){
        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
        jdbcDao.setDataSource(dataSource);
        jdbcDao.setUsersByUsernameQuery("select username, password, enabled from user where username=?");
        jdbcDao.setAuthoritiesByUsernameQuery("select b.username, a.role "+
                " from role a join user b on a.userId = b.id where b.username=?");
        return jdbcDao;
    }

}
