package com.omao.wellness.product.service;

import com.omao.wellness.product.model.Ingredient;
import com.omao.wellness.product.model.Product;
import com.omao.wellness.product.repository.IngredientRepository;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product insertProduct(Product product);
    Ingredient insertIngredient(Ingredient ingredient);
    Optional<List<Product>> saveAllProducts(List<Product> products);
    Optional<List<Ingredient>> saveAllIngredients(List<Ingredient> ingredients);
    List<Ingredient> insertManyIngredients(Collection<Ingredient> ingredients);

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
