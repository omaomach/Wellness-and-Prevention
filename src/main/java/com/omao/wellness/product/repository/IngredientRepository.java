package com.omao.wellness.product.repository;

import com.omao.wellness.product.model.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient,Long> {
    Optional<Ingredient> findById(Long id);
    Page<Ingredient> findByIngredientName(String name, Pageable pageable);
    Page<Ingredient> findByIngredientQuantity(String ingredientQuantity,Pageable pageable);
}
