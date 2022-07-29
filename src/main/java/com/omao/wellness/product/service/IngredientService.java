package com.omao.wellness.product.service;

import com.omao.wellness.product.model.Ingredient;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.Optional;

public interface IngredientService {
    Ingredient saveIngredient(Ingredient ingredient);
    Optional<Collection<Ingredient>> saveManyIngredients(Collection<Ingredient> ingredients);
    Optional<Ingredient> getById(Long id);
    Page<Ingredient> getByIngredientName(String ingredientName,int pageNumber);
    Page<Ingredient> getByIngredientQuantity(String ingredientQuantity,int pageNumber);
    Optional<Ingredient> updateIngredient(Ingredient  ingredient);
    void deleteIngredientById(Long id);
    void deleteIngredient(Ingredient ingredient);
    void deleteManyIngredients(Collection<Ingredient> ingredients);


}
