package com.omao.wellness.product.service;

import com.omao.wellness.product.model.Ingredient;
import com.omao.wellness.product.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class IngredientServiceImplementation implements IngredientService{
    private final IngredientRepository ingredientRepository;
    @Override
    public Ingredient saveIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Optional<Collection<Ingredient>> saveManyIngredients(Collection<Ingredient> ingredients) {
        return Optional.of(ingredientRepository.saveAll(ingredients));
    }

    @Override
    public Optional<Ingredient> getById(Long id) {
        return ingredientRepository.findById(id);
    }

    @Override
    public Page<Ingredient> getByIngredientName(String ingredientName, int pageNumber) {
        return ingredientRepository.findByIngredientName(ingredientName, PageRequest.of(pageNumber,30));
    }

    @Override
    public Page<Ingredient> getByIngredientQuantity(String ingredientQuantity, int pageNumber) {
        return ingredientRepository.findByIngredientQuantity(ingredientQuantity,PageRequest.of(pageNumber,30));
    }

    @Override
    @Transactional
    public Optional<Ingredient> updateIngredient(Ingredient ingredient) {
        Optional<Ingredient> ingred = ingredientRepository.findById(ingredient.getId());
        if (ingred.isPresent()){
            ingred.get().setIngredientName(ingredient.getIngredientName());
            ingred.get().setIngredientQuantity(ingredient.getIngredientQuantity());
        }
        return Optional.empty();
    }

    @Override
    public void deleteIngredientById(Long id) {
        ingredientRepository.deleteById(id);
    }

    @Override
    public void deleteIngredient(Ingredient ingredient) {
        ingredientRepository.delete(ingredient);
    }

    @Override
    public void deleteManyIngredients(Collection<Ingredient> ingredients) {
        ingredientRepository.deleteAll(ingredients);
    }
}
