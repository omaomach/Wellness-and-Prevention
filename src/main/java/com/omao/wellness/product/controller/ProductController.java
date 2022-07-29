package com.omao.wellness.product.controller;

import com.omao.wellness.product.model.Product;
import com.omao.wellness.product.model.ProductPost;
import com.omao.wellness.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(value = "/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Product> saveProduct(@RequestBody ProductPost product) {
        return ResponseEntity.status(CREATED).body(productService
                .insertProduct(
                        new Product(
                                null,
                                product.getProductName(),
                                product.getProductDescription(),
                                product.getSuggestedUse(),
                                product.getCategory(),
                                product.getDosage(),
                                product.getExpirationDate(),
                                product.getIngredientsPost())
                )
        );
    }

    @PostMapping(value = "/add/many", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> saveManyProducts(@RequestBody Collection<ProductPost> products) throws InterruptedException {
        List<Product> prods = new ArrayList<>();
      products.forEach(productPost ->
              prods.add(new Product(
              null,productPost.getProductName(),
              productPost.getProductDescription(),
              productPost.getSuggestedUse(),
              productPost.getCategory(),
              productPost.getDosage(),
              productPost.getExpirationDate(),
              productPost.getIngredientsPost()
      )));
        return ResponseEntity.status(CREATED).body(productService.insertManyProducts(prods));
    }

    @GetMapping(value = "/{page_number}", produces = "application/json")
    public ResponseEntity<?> getAll(@PathVariable(value = "page_number") String pageNumber) {
        return ResponseEntity.status(OK).body(productService.getAllProducts(Integer.parseInt(pageNumber) - 1));
    }

    @GetMapping(value = "/{product_name}/{page_number}", produces = "application/json")
    public ResponseEntity<?> getUsingProductName(@PathVariable(value = "product_name") String productName, @PathVariable(value = "page_number") String pageNumber) {
        return ResponseEntity.status(OK).body(productService.getProductWithName(productName, Integer.parseInt(pageNumber) - 1));
    }

    @GetMapping(value = "/desc/{page_number}", produces = "application/json")
    public ResponseEntity<?> getUsingProductDescription(@RequestBody String description, @PathVariable(value = "page_number") String pageNumber) {
        return ResponseEntity.status(OK).body(productService.getProductWithDescription(description, Integer.parseInt(pageNumber) - 1));
    }

    @GetMapping(value = "/use/{page_number}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> getUsingProductSuggestedUse(@RequestBody String use, @PathVariable(value = "page_number") String pageNumber) {
        return ResponseEntity.status(OK).body(productService.getProductWithSuggestedUse(use, Integer.parseInt(pageNumber) - 1));
    }

    @PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        return ResponseEntity.status(ACCEPTED).body(productService.updateProduct(product));
    }

    @DeleteMapping(value = "/delete", consumes = "application/json")
    public ResponseEntity<?> deleteProduct(@RequestBody Product product) {
        productService.deleteProduct(product);
        return ResponseEntity.status(ACCEPTED).body("Product deleted successfully...\uD83D\uDE0A");
    }

    @DeleteMapping(value = "/delete/{product_name}", consumes = "application/json")
    public ResponseEntity<?> deleteProductUsingName(@PathVariable(value = "product_name") String productName) {
        productService.deleteProductByName(productName);
        return ResponseEntity.status(ACCEPTED).body("Product deleted successfully...\uD83D\uDE0A");
    }

    @DeleteMapping(value = "/delete/{id}", consumes = "application/json")
    public ResponseEntity<?> deleteProductUsingId(@PathVariable String id) {
        productService.deleteProductById(Long.parseLong(id));
        return ResponseEntity.status(ACCEPTED).body("Product deleted successfully...\uD83D\uDE0A");
    }

    @DeleteMapping(value = "/delete/many", consumes = "application/json")
    public ResponseEntity<?> deleteManyProducts(@RequestBody Collection<Product> products) {
        productService.deleteManyProducts(products);
        return ResponseEntity.status(ACCEPTED).body("Product deleted successfully...\uD83D\uDE0A");
    }
}
