package dev.hectorgallego.springbootrestapi.mapperdto;

import dev.hectorgallego.springbootrestapi.model.product.Product;
import dev.hectorgallego.springbootrestapi.model.product.ProductDto;

public class ProductMaper {

    public static ProductDto mapperProduct(Product product) {

        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getImgUrl(),
                product.getPrice(),
                product.getCategories());

    }

}
