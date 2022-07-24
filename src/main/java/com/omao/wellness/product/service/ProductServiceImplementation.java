package com.omao.wellness.product.service;

import com.omao.wellness.product.model.Ingredient;
import com.omao.wellness.product.model.Product;
import com.omao.wellness.product.repository.IngredientRepository;
import com.omao.wellness.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImplementation implements ProductService {
    private final ProductRepository productRepository;
    private final IngredientRepository ingredientRepository;

    @Override
    public Product insertProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Ingredient insertIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Optional<List<Product>> saveAllProducts(List<Product> products) {
        return Optional.of(productRepository.saveAll(products));
    }

    @Override
    public Optional<List<Ingredient>> saveAllIngredients(List<Ingredient> ingredients) {
        return Optional.of(ingredientRepository.saveAll(ingredients));
    }

    @Override
    public List<Ingredient> insertManyIngredients(Collection<Ingredient> ingredients) {
        return ingredientRepository.saveAll(ingredients);
    }


    @Override
    public Optional<?> insertManyProducts(Collection<Product> products) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        Thread thread = new Thread(() -> productRepository.saveAll(products));
        thread.start();
        latch.wait(3000);
        //noinspection deprecation
        thread.stop();
        return Optional.of("Products inserted successfully...\uD83D\uDE0A");
    }

    @Override
    public Optional<Page<Product>> getAllProducts(int pageNumber) {
        return Optional.of(productRepository.findAll(PageRequest.of(pageNumber, 30)));
    }

    @Override
    public Optional<Page<Product>> getProductWithName(String name, int pageNumber) {
        return Optional.of(productRepository.findByProductName(name, PageRequest.of(pageNumber, 30)));
    }

    @Override
    public Optional<Page<Product>> getProductWithDescription(String description, int pageNumber) {
        return Optional.of(productRepository.findByProductDescription(description, PageRequest.of(pageNumber, 30)));
    }

    @Override
    public Optional<Page<Product>> getProductWithSuggestedUse(String use, int pageNumber) {
        return Optional.of(productRepository.findBySuggestedUse(use, PageRequest.of(pageNumber, 30)));
    }


    @Override
    @Transactional
    public Optional<Product> updateProduct(Product product) {
        Optional<Product> prod = productRepository.findById(product.getId());
        if (prod.isPresent()) {
            prod.get().setId(product.getId());
            prod.get().setProductName(product.getProductName());
            prod.get().setProductDescription(product.getProductDescription());
            prod.get().setIngredients(product.getIngredients());
            prod.get().setSuggestedUse(product.getSuggestedUse());
        }
        return prod;
    }


    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    @Override
    public void deleteProductByName(String productName) {
        Optional<Product> product = productRepository.findByProductName(productName);
        product.ifPresent(productRepository::delete);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void deleteManyProducts(Collection<Product> products) {
        productRepository.deleteAll(products);
    }
}
