package com.omao.wellness.product.controller;

import com.omao.wellness.product.model.Ingredient;
import com.omao.wellness.product.model.IngredientPost;
import com.omao.wellness.product.service.IngredientService;
import com.omao.wellness.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(value = "/api/v1/ingredients")
@RequiredArgsConstructor
public class IngredientsController {
    private final IngredientService ingredientService;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addIngredient(@RequestBody IngredientPost ingredientPost) {
        return ResponseEntity.status(CREATED).body(ingredientService.saveIngredient(new Ingredient(null, ingredientPost.getIngredientName(), ingredientPost.getIngredientQuantity())));
    }

    @PostMapping(value = "/add-many")
    public ResponseEntity<?> addManyIngredients(@RequestBody Collection<IngredientPost> ingredientPosts) {
        var list = new ArrayList<Ingredient>();
        for (IngredientPost ingredientPost : ingredientPosts) {
            list.add(new Ingredient(null, ingredientPost.getIngredientName(), ingredientPost.getIngredientQuantity()));
        }
        Optional<Collection<Ingredient>> ingredients = ingredientService.saveManyIngredients(list);
        return ResponseEntity.status(CREATED).body(ingredients);
    }

    @GetMapping(value = "/getbyid/{id}", produces = "application/json")
    public ResponseEntity<?> getIngredientById(@PathVariable(name = "id") String id) {
        return ResponseEntity.status(OK).body(ingredientService.getById(Long.parseLong(id)));
    }

    @GetMapping(value = "/getbyname/{name}/{page_number}", produces = "application/json")
    public ResponseEntity<?> getIngredientByName(@PathVariable(name = "name") String name, @PathVariable(name = "page_number") String pageNumber) {
        return ResponseEntity.status(OK).body(ingredientService.getByIngredientName(name, Integer.parseInt(pageNumber) - 1));
    }

    @GetMapping(value = "/getquantity/{quantity}/{page_number}", produces = "application/json")
    public ResponseEntity<?> getIngredientsByQuantity(@PathVariable(name = "quantity") String quantity, @PathVariable(name = "page_number") String pageNumber) {
        return ResponseEntity.status(OK).body(ingredientService.getByIngredientQuantity(quantity, Integer.parseInt(pageNumber) - 1));
    }

    @PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateIngredient(@RequestBody Ingredient ingredient) {
        return ResponseEntity.status(OK).body(ingredientService.updateIngredient(ingredient));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") String id) {
        ingredientService.deleteIngredientById(Long.parseLong(id));
        return ResponseEntity.status(ACCEPTED).body("Ingredient successfully delete");
    }

}
