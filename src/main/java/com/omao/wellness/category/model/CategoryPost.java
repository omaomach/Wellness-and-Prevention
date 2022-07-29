package com.omao.wellness.category.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryPost {
    private String categoryName;
    private String description;
    private String imageUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryPost that = (CategoryPost) o;
        return Objects.equals(categoryName, that.categoryName) && Objects.equals(description, that.description) && Objects.equals(imageUrl, that.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryName, description, imageUrl);
    }
}
