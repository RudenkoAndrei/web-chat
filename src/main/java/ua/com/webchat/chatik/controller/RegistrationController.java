package ua.com.webchat.chatik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.webchat.chatik.data.dto.UserDTO;
import ua.com.webchat.chatik.data.entity.Role;
import ua.com.webchat.chatik.data.entity.User;
import ua.com.webchat.chatik.data.entity.VerificationToken;
import ua.com.webchat.chatik.data.repository.RoleRepository;
import ua.com.webchat.chatik.data.repository.UserRepository;
import ua.com.webchat.chatik.registration.OnRegistrationCompleteEvent;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;

/**
 * @author Rudenko Andrey
 * @project chatik
 */

@RestController
public class RegistrationController {

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    MessageSource messageSource;

    @Autowired
    RoleRepository roleRepository;

    @RequestMapping(value = "/registration")
    public ModelAndView registerNewAccount(@ModelAttribute("user")
                                           @Valid UserDTO accountDTO,
                                           BindingResult result,
                                           WebRequest webRequest){
        if(result.hasErrors()){
            return new ModelAndView("registration", "user", accountDTO);
        }

        User registeredUser = userRepository.registerUserAccount(accountDTO);
        if(registeredUser == null){
            result.rejectValue("email", "message.hasError");
        }
        try {
            String appUrl = webRequest.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registeredUser, webRequest.getLocale(), appUrl));
        }
        catch (Exception ex){
            System.out.println("Email registration failed: " + accountDTO.getEmail());
            ex.printStackTrace();
            return new ModelAndView("newlogin", "customMessage",
                    "Email registration was failed: "+accountDTO.getEmail());
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("newlogin");
        modelAndView.addObject("customMessage",
                "Please check your email: "+ accountDTO.getEmail());

        return modelAndView;
    }

    @GetMapping(value = "/regConfirm")
    public ModelAndView confirm(WebRequest webRequest,
                                @RequestParam("token") String token){
        Locale locale = webRequest.getLocale();

        VerificationToken verificationToken = userRepository.getVerificationToken(token);
        if(verificationToken == null){
            String message = messageSource.getMessage("auth.message.invalidToken", null, locale);
            return new ModelAndView("newlogin", "customMessage", message);
        }

        User user = verificationToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if((verificationToken.getExpiryDate().getTime() - calendar.getTime().getTime()) <= 0){
            String message = messageSource.getMessage("auth.message.expired", null, locale);
            return new ModelAndView("newlogin", "customMessage", message);
        }

        user.setEnabled(true);

        userRepository.save(user);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("newlogin");
        modelAndView.addObject("customMessage","User was registered!");
        return modelAndView;
    }
}
