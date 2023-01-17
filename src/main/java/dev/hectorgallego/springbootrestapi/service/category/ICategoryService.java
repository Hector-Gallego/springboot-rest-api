package dev.hectorgallego.springbootrestapi.service.category;

import java.util.List;

import dev.hectorgallego.springbootrestapi.model.Category;

public interface ICategoryService {

    Category getCategoryById(Long id);
    List<Category> getCategories();
    Category createCategory(Category category);
    Category updCategory(Category category, Long id);
    void deleteCategoryById(Long id);
    
}
