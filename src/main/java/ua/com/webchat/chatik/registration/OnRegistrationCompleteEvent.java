package ua.com.webchat.chatik.registration;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import ua.com.webchat.chatik.data.entity.User;

import java.util.Locale;

/**
 * @author Rudenko Andrey
 * @project chatik
 */
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private String appUrl;
    private User user;
    private Locale locale;

    public OnRegistrationCompleteEvent(User user, Locale locale, String appUrl) {
        super(user);
        this.user = user;
        this.appUrl = appUrl;
        this.locale = locale;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

}
