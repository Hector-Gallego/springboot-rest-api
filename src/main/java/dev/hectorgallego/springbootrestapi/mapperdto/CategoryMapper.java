package dev.hectorgallego.springbootrestapi.mapperdto;

import dev.hectorgallego.springbootrestapi.model.category.Category;
import dev.hectorgallego.springbootrestapi.model.category.CategoryDto;

public class CategoryMapper {

    public static CategoryDto mapperCategory(Category category) {

        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getDescription());
    }

}
