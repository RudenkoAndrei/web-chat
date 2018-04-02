package ua.com.webchat.chatik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.webchat.chatik.data.entity.User;
import ua.com.webchat.chatik.data.repository.UserRepository;

/**
 * @author Rudenko Andrey
 * @project chatik
 */

@RestController
@RequestMapping("/registered")
public class RegisteredUsers {

    @Autowired
    UserRepository repository;

    @GetMapping("/all")
    public Iterable<User> getUsers(){
        return repository.findAll();
    }
}
