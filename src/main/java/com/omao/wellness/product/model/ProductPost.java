package com.omao.wellness.product.model;

import com.omao.wellness.category.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductPost {
    private String productName;
    private String productDescription;
    private String suggestedUse;
    private Category category;
    private Collection<Ingredient> ingredients;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductPost that = (ProductPost) o;
        return Objects.equals(productName, that.productName) && Objects.equals(productDescription, that.productDescription) && Objects.equals(suggestedUse, that.suggestedUse) && Objects.equals(category, that.category) && Objects.equals(ingredients, that.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, productDescription, suggestedUse, category, ingredients);
    }
}
