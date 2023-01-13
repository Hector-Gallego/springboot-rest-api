package dev.hectorgallego.springbootrestapi.events;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;


@Component
public class RegisterUserPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public RegisterUserPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishRegisterUserEvent(RegisterUserEvent registerUserEvent){
        eventPublisher.publishEvent(registerUserEvent);
    }
    
}
