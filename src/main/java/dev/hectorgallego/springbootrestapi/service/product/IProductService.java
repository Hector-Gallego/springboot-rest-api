package dev.hectorgallego.springbootrestapi.service.product;

import java.util.List;

import dev.hectorgallego.springbootrestapi.model.Product;

public interface IProductService {

    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product createProduct(Product product);
    Product updateProduct(Product product, Long id);
    void deleteProduct(Long id);
    List<Product> getProductsByCategory(String name);
    Product getProductByName(String name);
    
}
