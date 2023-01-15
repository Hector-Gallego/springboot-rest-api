package dev.hectorgallego.springbootrestapi.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record Login(

    @NotBlank
    @Email
    String email,
    @NotBlank
    String password) {  
        
}
