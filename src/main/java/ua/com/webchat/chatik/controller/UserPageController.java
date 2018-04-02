package ua.com.webchat.chatik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.com.webchat.chatik.data.entity.User;
import ua.com.webchat.chatik.data.repository.UserRepository;

/**
 * @author Rudenko Andrey
 * @project chatik
 */
@Controller
public class UserPageController {

    @Autowired
    UserRepository userRepository;


    @RequestMapping("/user/{username}")
    public ModelAndView userPage(@PathVariable("username") String username){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("user");
        User currentUser = userRepository.findUserByUsername(username);
        modelAndView.addObject("currentUser", currentUser);
        return modelAndView;
    }
}
