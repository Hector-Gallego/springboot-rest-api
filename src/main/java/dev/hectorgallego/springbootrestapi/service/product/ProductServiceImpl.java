package dev.hectorgallego.springbootrestapi.service.product;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.hectorgallego.springbootrestapi.model.Product;
import dev.hectorgallego.springbootrestapi.repository.CategoryRepository;
import dev.hectorgallego.springbootrestapi.repository.ProductRepository;

@Service
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    
    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("no se encontro al producto con id: " + id));
    }

    @Override
    @Transactional
    public Product createProduct(Product product) {

        /*
         * List<Category> categories = product.getCategories();
         * List<Category> categoriesPersist = new ArrayList();
         * 
         * for(Category category : categories){
         * categoriesPersist.add(categoryRepository.findById(category.getId()).get());
         * }
         * 
         * product.setCategories(categoriesPersist);
         * return productRepository.save(product);
         * 
         */

        product.setCategories(product.getCategories()
                .stream()
                .map((category) -> categoryRepository.findById(category.getId()).get())
                .collect(Collectors.toList()));

        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product updateProduct(Product product, Long id) {

        Product productPersist = getProductById(id);

        /* 
        productPersist.setName(product.getName());
        productPersist.setCategories(product.getCategories());
        productPersist.setImgUrl(product.getImgUrl());
        productPersist.setDescription(product.getDescription());
        productPersist.setPrice(product.getPrice());
        */
        
        BeanUtils.copyProperties(product, productPersist, "id");
        

        return createProduct(productPersist);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        getProductById(id);
        productRepository.deleteById(id);

    }


    @Override
    public List<Product> getProductsByCategory(String name) {
        return productRepository.findByCategoryName(name);
    }



    @Override
    public Product getProductByName(String name) {
        // TODO Auto-generated method stub
        return productRepository.findByName(name).orElseThrow(()-> new NoSuchElementException("no se encontro al producto con nombre: "+ name));
    }

}
