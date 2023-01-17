package dev.hectorgallego.springbootrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.hectorgallego.springbootrestapi.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
