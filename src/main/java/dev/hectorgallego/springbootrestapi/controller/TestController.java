package dev.hectorgallego.springbootrestapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {


    @GetMapping("/admin")
    public String adminSesion(){
        return "solo para administradores";
    }

    @GetMapping("/useradmin")
    public String userAdminSesion(){
        return "pasar usuarios y administradores";
    }

    
}
