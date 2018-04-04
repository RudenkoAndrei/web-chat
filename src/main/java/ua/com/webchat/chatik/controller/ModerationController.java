package ua.com.webchat.chatik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.webchat.chatik.data.entity.User;
import ua.com.webchat.chatik.data.repository.UserRepository;

import java.util.List;

/**
 * @author Rudenko Andrey
 * @project chatik
 */
@Controller
public class ModerationController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/moderate", method = RequestMethod.GET)
    public ModelAndView mainModeratePage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mainmoderate");
        List<User> userList = userRepository.findAll();
        modelAndView.addObject("userList", userList);
        return modelAndView;
    }

    @RequestMapping(value = "/moderate/{username}", method = RequestMethod.GET)
    public ModelAndView secondaryModeratePage(@PathVariable("username") String username){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("moderate");

        User currentUser = userRepository.findUserByUsername(username);
        modelAndView.addObject("currentUser", currentUser);

        return modelAndView;
    }

    @RequestMapping(value = "/moderate/{username}/edit", method = RequestMethod.GET)
    public ModelAndView initEditModerate(@PathVariable("username") String username){
        User user = userRepository.findUserByUsername(username);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("moderateform");
        return modelAndView;
    }

    @RequestMapping(value = "moderate/{username}/edit")
    public ModelAndView confirmEditModerate(@ModelAttribute("user") User user, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return new ModelAndView("moderateform");
        }
        else {
            ModelAndView modelAndView = new ModelAndView();
            userRepository.save(user);
            modelAndView.setViewName("moderateform");
            return modelAndView;
        }
    }

}
