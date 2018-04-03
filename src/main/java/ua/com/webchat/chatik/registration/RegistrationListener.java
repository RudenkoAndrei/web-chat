package ua.com.webchat.chatik.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import ua.com.webchat.chatik.data.entity.User;
import ua.com.webchat.chatik.data.repository.UserRepository;

import java.util.Locale;
import java.util.UUID;

/**
 * @author Rudenko Andrey
 * @project chatik
 */
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Qualifier("getMyMessageSource")
    private MessageSource messageSource;

    @Autowired
    @Qualifier("getJavaMailSender")
    private JavaMailSender mailSender;


    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent onRegistrationCompleteEvent) {
        this.confirmRegistration(onRegistrationCompleteEvent);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event){
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userRepository.createVerificationToken(user, token);

        String address = user.getEmail();
        String subject = "Registration confirmation";
        String confirmationURL = event.getAppUrl() + "/regConfirm?token="+ token;

        String message = messageSource.getMessage("message.regSuccess", null, event.getLocale());

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("ras97777@gmail.com");
        simpleMailMessage.setTo(address);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message + "\r\n " + "http://webapp.cloudapp.net/"+confirmationURL);

        mailSender.send(simpleMailMessage);

    }

    @Bean
    public MessageSource getMyMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setCacheSeconds(10);
        return messageSource;
    }
}
