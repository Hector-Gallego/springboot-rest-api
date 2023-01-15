package dev.hectorgallego.springbootrestapi.events;


import java.io.UnsupportedEncodingException;

import org.springframework.context.event.EventListener;
//import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import dev.hectorgallego.springbootrestapi.model.User;
import dev.hectorgallego.springbootrestapi.service.SendVerificationEmailService;
import dev.hectorgallego.springbootrestapi.service.User.IUserService;
import jakarta.mail.MessagingException;


@Component
public class RegisterUserListener {

    private final IUserService userService;
    private final SendVerificationEmailService sendVerificationEmailService;

    public RegisterUserListener(IUserService userService, SendVerificationEmailService sendVerificationEmailService) {
        this.userService = userService;
        this.sendVerificationEmailService = sendVerificationEmailService;
    }

    //@Async
    @EventListener
    public void registerUser(RegisterUserEvent registerUserEvent) throws UnsupportedEncodingException, MessagingException {

        User user = registerUserEvent.getUser();
        String url = registerUserEvent.getUrl();

        userService.createUser(user);
        sendVerificationEmailService.sendVerificationEmail(user, url);
        

    }
    
}
