package dev.hectorgallego.springbootrestapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.hectorgallego.springbootrestapi.model.user.User;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByTokenVerification(String token);
}


