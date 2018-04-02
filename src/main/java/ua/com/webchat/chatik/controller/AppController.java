package ua.com.webchat.chatik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.com.webchat.chatik.data.entity.User;
import ua.com.webchat.chatik.data.repository.UserRepository;

import java.security.Principal;
import java.util.List;

/**
 * @author Rudenko Andrey
 * @project chatik
 */

@Controller
public class AppController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView getIndex(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/newlogin")
    public ModelAndView getLogin(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("newlogin");
        return modelAndView;
    }

    @GetMapping(value = {"/chat/**"})
    public ModelAndView chat(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "Chat!");
        modelAndView.setViewName("chat");
        return modelAndView;
    }

    @GetMapping("/registeredusers")
    public ModelAndView registeredUserPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registeredusers");

        List<User> userList = userRepository.findAll();
        modelAndView.addObject("userList", userList);
        return modelAndView;
    }

    @GetMapping("/accessDenied")
    public ModelAndView myAccessDenied(Principal user){
        ModelAndView modelAndView = new ModelAndView();
        if(user != null){
            modelAndView.addObject("message", "H1, " + user.getName()
                    + " you don`t have a permission to get here!");
        }
        else {
            modelAndView.addObject("message", "You do not have permission to access this page!");
        }
        modelAndView.setViewName("accessDenied");
        return modelAndView;
    }

}
