package dev.hectorgallego.springbootrestapi.service.category;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.hectorgallego.springbootrestapi.model.Category;
import dev.hectorgallego.springbootrestapi.repository.CategoryRepository;


@Service
public class CategoryServiceImpl implements ICategoryService{


    private final CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Transactional(readOnly = true)
    @Override
    public Category getCategoryById(Long id) {

        return categoryRepository
            .findById(id)
            .orElseThrow(() -> new NoSuchElementException("No se encontro al producto con id: "+ id));

    }

    @Transactional(readOnly = true)
    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }


    @Transactional
    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }


    @Override
    @Transactional
    public Category updCategory(Category category, Long id) {
        
        Category categoryPersist = getCategoryById(id);

        categoryPersist.setName(category.getName());
        categoryPersist.setDescription(category.getDescription());

        return createCategory(categoryPersist);
    }


    @Override
    @Transactional
    public void deleteCategoryById(Long id) {


        Category category = getCategoryById(id);

        /* 
        for(Product product : category.getProducts()){
            System.out.println("========== removiendo categoria ===========");
            product.getCategories().remove(category);
        }
        */

        category.getProducts().forEach((product) -> product.getCategories().remove(category));
        categoryRepository.deleteById(id); 
    
    }
    
}
