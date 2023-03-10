package dev.hectorgallego.springbootrestapi.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.hectorgallego.springbootrestapi.events.RegisterUserEvent;
import dev.hectorgallego.springbootrestapi.events.RegisterUserPublisher;
import dev.hectorgallego.springbootrestapi.model.Login;
import dev.hectorgallego.springbootrestapi.model.user.User;
import dev.hectorgallego.springbootrestapi.model.user.UserDto;
import dev.hectorgallego.springbootrestapi.service.TokenGeneretorService;
import dev.hectorgallego.springbootrestapi.service.TokenVerificationService;
import dev.hectorgallego.springbootrestapi.service.User.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final TokenGeneretorService tokenService;
    private final AuthenticationManager authManager;
    private final RegisterUserPublisher registerUserPublisher;
    private final TokenVerificationService tokenVerificationService;
    private final IUserService userService;

    public LoginController(TokenGeneretorService tokenService, AuthenticationManager authManager,
            RegisterUserPublisher registerUserPublisher, TokenVerificationService tokenVerificationService,
            IUserService userService) {
        this.tokenService = tokenService;
        this.authManager = authManager;
        this.registerUserPublisher = registerUserPublisher;
        this.tokenVerificationService = tokenVerificationService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public String loginUser(@Valid @RequestBody Login login) {


            Authentication auth = authManager
                    .authenticate(new UsernamePasswordAuthenticationToken(login.email(), login.password()));
            return tokenService.GenerateToken(auth);

    }

    @PostMapping("/register")
    public UserDto registerUserController(@Valid @RequestBody User user, HttpServletRequest request)
            throws InterruptedException {


        String url = request.getRequestURL()
                .toString()
                .replace(request.getServletPath(), "");



        registerUserPublisher.publishRegisterUserEvent(new RegisterUserEvent(user, url));

        return userService.getUserByEmail(user.getEmail());

    }

    @GetMapping("/verification/{token}")
    public void verificationToken(@PathVariable String token) {
        tokenVerificationService.tokenVerification(token);
    }
}
