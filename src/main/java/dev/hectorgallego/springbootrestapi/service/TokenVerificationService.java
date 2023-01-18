package dev.hectorgallego.springbootrestapi.service;

import org.springframework.stereotype.Service;

import dev.hectorgallego.springbootrestapi.model.user.User;
import dev.hectorgallego.springbootrestapi.repository.UserRepository;

@Service
public class TokenVerificationService {


    private final UserRepository userRepository;

    public TokenVerificationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void tokenVerification(String token){

        User user = userRepository.findByTokenVerification(token).get();
        user.setEnabled(true);
        user.setTokenVerificaton("null");
        userRepository.save(user);

    }

    
}
