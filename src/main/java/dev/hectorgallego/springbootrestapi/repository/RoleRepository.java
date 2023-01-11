package dev.hectorgallego.springbootrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.hectorgallego.springbootrestapi.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
