package dev.hectorgallego.springbootrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.hectorgallego.springbootrestapi.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
}
