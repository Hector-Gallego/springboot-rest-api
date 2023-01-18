package dev.hectorgallego.springbootrestapi.service.category;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.hectorgallego.springbootrestapi.mapperdto.CategoryMapper;
import dev.hectorgallego.springbootrestapi.model.category.Category;
import dev.hectorgallego.springbootrestapi.model.category.CategoryDto;
import dev.hectorgallego.springbootrestapi.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryDto getCategoryById(Long id) {

        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("No se encontro al producto con id: " + id));

        return CategoryMapper.mapperCategory(category);

    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoryDto> getCategories() {
        return categoryRepository
                .findAll().stream()
                .map(category -> CategoryMapper.mapperCategory(category))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public Category updCategory(Category category, Long id) {

        Category categoryPersist = categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("no se encontro la categoria con id: " + id));

        categoryPersist.setName(category.getName());
        categoryPersist.setDescription(category.getDescription());

        return createCategory(categoryPersist);
    }

    @Override
    @Transactional
    public void deleteCategoryById(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("no se encontro la categoria con id: " + id));

        /*
         * for(Product product : category.getProducts()){
         * System.out.println("========== removiendo categoria ===========");
         * product.getCategories().remove(category);
         * }
         */

        category.getProducts()
                .forEach((product) -> product
                        .getCategories()
                        .remove(category));

        categoryRepository.deleteById(id);

    }

}
