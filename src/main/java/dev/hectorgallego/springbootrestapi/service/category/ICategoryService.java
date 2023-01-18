package dev.hectorgallego.springbootrestapi.service.category;

import java.util.List;

import dev.hectorgallego.springbootrestapi.model.category.Category;
import dev.hectorgallego.springbootrestapi.model.category.CategoryDto;

public interface ICategoryService {

    CategoryDto getCategoryById(Long id);
    List<CategoryDto> getCategories();
    Category createCategory(Category category);
    Category updCategory(Category category, Long id);
    void deleteCategoryById(Long id);
    
}
