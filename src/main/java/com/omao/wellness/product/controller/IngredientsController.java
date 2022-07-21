package com.omao.wellness.product.controller;

import com.omao.wellness.product.model.Ingredient;
import com.omao.wellness.product.model.IngredientPost;
import com.omao.wellness.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/api/v1/ingredients")
@RequiredArgsConstructor
public class IngredientsController {
    private final ProductService productService;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addIngredient(@RequestBody IngredientPost ingredientPost) {
        return ResponseEntity.status(CREATED).body(productService.insertIngredient(new Ingredient(null, ingredientPost.getIngredientName(), ingredientPost.getIngredientQuantity())));
    }

    @PostMapping(value = "/add-many")
    public ResponseEntity<?> addManyIngredients(@RequestBody Collection<IngredientPost> ingredientPosts) {
        var list = new ArrayList<Ingredient>();
        for (IngredientPost ingredientPost : ingredientPosts) {
            list.add(new Ingredient(null,ingredientPost.getIngredientName(),ingredientPost.getIngredientQuantity()));
        }
        Optional<List<Ingredient>> ingredients = productService.saveAllIngredients(list);
        return ResponseEntity.status(CREATED).body(ingredients);
    }

}
