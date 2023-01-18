package dev.hectorgallego.springbootrestapi.model.user;

import java.util.List;

import dev.hectorgallego.springbootrestapi.model.Role;

public record UserDto(Long id, String firstName, String lastName, String email, List<Role> roles) {
}
