package dev.hectorgallego.springbootrestapi.service.product;

import java.util.List;

import dev.hectorgallego.springbootrestapi.model.product.Product;
import dev.hectorgallego.springbootrestapi.model.product.ProductDto;

public interface IProductService {

    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long id);
    Product createProduct(Product product);
    Product updateProduct(Product product, Long id);
    void deleteProduct(Long id);
    List<ProductDto> getProductsByCategory(String name);
    ProductDto getProductByName(String name);
    
}
