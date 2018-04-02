package ua.com.webchat.chatik.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ua.com.webchat.chatik.security.MyAccessDenied;

/**
 * @author Rudenko Andrey
 * @project chatik
 */

@Configuration
@ComponentScan(basePackages = {"ua.com.webchat.chatik.security"})
//@EnableWebMvc
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public SecurityConfig(){
        super();
    }

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder managerBuilder){
        try {
            managerBuilder.inMemoryAuthentication()
                    .withUser("ServerUser")
                    .password("super1234password098newerencode")
                    .roles("USER");
            managerBuilder.inMemoryAuthentication()
                    .withUser("ServerAdmin")
                    .password("super1234password098newerencode")
                    .roles("ADMIN");
            managerBuilder.inMemoryAuthentication()
                    .withUser("ServerSuperadmin")
                    .password("super1234password098newerencode")
                    .roles("SUPERADMIN");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                    .antMatchers("/**/*.css", "/**/*.js");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                    .authorizeRequests()
                        .antMatchers("/")
                .permitAll()
                //.anyRequest().permitAll()
                .antMatchers("/chat", "/user")
                        .access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_SUPERADMIN')")
                            .antMatchers("/moderate/**/**")
                                .access("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN')")
                            .and()
                                .formLogin()
                                    .loginPage("/newlogin")
                                        .usernameParameter("username")
                                        .passwordParameter("password")
                                        //where user forward after authenticating successfully
                                        .defaultSuccessUrl("/", true)
                                    .permitAll()
                            .and()
                                    .logout()
                                        .permitAll()
                                .and()
                                    .exceptionHandling()
                                        .accessDeniedHandler(accessDeniedHandler())
                                            .accessDeniedPage("/accessDenied");
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new MyAccessDenied();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
}
