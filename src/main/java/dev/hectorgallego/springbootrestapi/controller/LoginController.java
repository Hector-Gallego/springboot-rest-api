package dev.hectorgallego.springbootrestapi.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.hectorgallego.springbootrestapi.model.Login;
import dev.hectorgallego.springbootrestapi.model.User;
import dev.hectorgallego.springbootrestapi.service.TokenGeneretorService;
import dev.hectorgallego.springbootrestapi.service.User.IUserService;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final TokenGeneretorService tokenService;
    private final AuthenticationManager authManager;
    private final IUserService userService;
    

    public LoginController(TokenGeneretorService tokenService, AuthenticationManager authManager, IUserService userService) {
        this.tokenService = tokenService;
        this.authManager = authManager;
        this.userService = userService;
    }

    

    @PostMapping("/login")
    public String loginUser(@RequestBody Login login){

        Authentication auth = authManager
            .authenticate(new UsernamePasswordAuthenticationToken(login.email(), login.password()));
        return tokenService.GenerateToken(auth);
    }

    @PostMapping("/register")
    public User registerUserController(@RequestBody User user){
        return userService.createUser(user);
    }
    
}
