package com.omao.wellness.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class IngredientPost {
    private String ingredientName;
    private String ingredientQuantity;
}