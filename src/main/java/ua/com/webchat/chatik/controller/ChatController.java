package ua.com.webchat.chatik.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ua.com.webchat.chatik.domen.Message;

import java.security.Principal;

/**
 * @author Rudenko Andrey
 * @project chatik
 */
@Controller
public class ChatController {

    @MessageMapping("/message")
    @SendTo("/chat/messages")
    public Message getMessage(Message message, final Principal principal){
        message.setFrom(principal.getName());
        System.out.println(message);
        return message;
    }
}
