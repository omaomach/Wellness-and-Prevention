package com.omao.wellness.product.service;

import com.omao.wellness.product.model.Product;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.Optional;

public interface ProductService {
    Product insertProduct(Product product);

    Optional<?> insertManyProducts(Collection<Product> products) throws InterruptedException;

    Optional<Page<Product>> getAllProducts(int pageNumber);

    Optional<Page<Product>> getProductWithName(String name, int pageNumber);

    Optional<Page<Product>> getProductWithDescription(String description, int pageNumber);

    Optional<Page<Product>> getProductWithSuggestedUse(String use, int pageNumber);


    Optional<Product> updateProduct(Product product);

    void deleteProduct(Product product);

    void deleteProductByName(String productName);

    void deleteProductById(Long Id);

    void deleteManyProducts(Collection<Product> products);
}
