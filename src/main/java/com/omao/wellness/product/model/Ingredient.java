package com.omao.wellness.product.model;

import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
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
