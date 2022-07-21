package com.omao.wellness.product.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.omao.wellness.category.model.Category;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "product_name", nullable = false)
    private String productName;
    @Column(name = "product_description", nullable = false)
    private String productDescription;
    @Column(name = "suggested_use", nullable = false)
    private String suggestedUse;
    @JoinColumn(name = "category", nullable = false)
    @OneToOne
    private Category category;
    @CollectionTable(name = "ingredients", joinColumns = @JoinColumn(name = "id"))
    @JsonProperty(value = "ingredients")
    @JoinColumn(name = "ingredients", nullable = false)
    @ManyToOne(targetEntity = Ingredient.class, fetch = EAGER)
    private Collection<Ingredient> ingredients;
}
