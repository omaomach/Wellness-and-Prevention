package com.omao.wellness.product.model;

import com.omao.wellness.category.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;
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
    private String dosage;
    private Date expirationDate;
    private Collection<Ingredient> ingredientsPost;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductPost that = (ProductPost) o;
        return Objects.equals(productName, that.productName) && Objects.equals(productDescription, that.productDescription) && Objects.equals(suggestedUse, that.suggestedUse) && Objects.equals(category, that.category) && Objects.equals(ingredientsPost, that.ingredientsPost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, productDescription, suggestedUse, category, ingredientsPost);
    }
}
