package com.omao.wellness.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;
    @Column(name = "ingredient_name", nullable = false)
    private String ingredientName;
    @Column(name = "ingredient_quantity", nullable = false)
    private String ingredientQuantity;
}
