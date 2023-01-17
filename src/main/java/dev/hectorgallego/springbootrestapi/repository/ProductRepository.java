package dev.hectorgallego.springbootrestapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.hectorgallego.springbootrestapi.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long >{

    @Query("SELECT p FROM Product p JOIN p.categories c WHERE c.name = :name")
    List<Product> findByCategoryName(String name); 
    Optional<Product> findByName(String name);
}
