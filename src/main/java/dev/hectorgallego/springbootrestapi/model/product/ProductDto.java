package dev.hectorgallego.springbootrestapi.model.product;

import java.util.List;

import dev.hectorgallego.springbootrestapi.model.category.Category;

public record ProductDto(Long id, String name, String description, String imgUrl, Double price, List<Category> categories) {
    
}
