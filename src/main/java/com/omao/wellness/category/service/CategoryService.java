package com.omao.wellness.category.service;

import com.omao.wellness.category.model.Category;
import com.omao.wellness.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Optional<Page<Category>> listAllCategories(int pageNumber) {
        return Optional.of(categoryRepository.findAll(PageRequest.of(pageNumber, 30)));
    }

    public Optional<Page<Category>> listCategoryByName(String name, int pageNumber) {
        return Optional.of(categoryRepository.findByCategoryName(name, PageRequest.of(pageNumber, 20)));
    }

    public Optional<Category> findCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Optional<Page<Category>> findCategoryContainingSearchWord(String word, int pageNumber) {
        return Optional.of(categoryRepository.findByDescriptionContainsIgnoreCase(word, PageRequest.of(pageNumber, 20)));
    }

    @Transactional
    public Optional<Category> update(Category category) {
        Optional<Category> cat = categoryRepository.findById(category.getId());
        if (cat.isPresent()) {
            cat.get().setId(category.getId());
            cat.get().setCategoryName(category.getCategoryName());
            cat.get().setDescription(category.getDescription());
            cat.get().setImageUrl(category.getImageUrl());
        }
        return cat;
    }

    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }

    public void deleteAllCategories() {
        categoryRepository.deleteAll();
    }

    public void deleteAllCategoriesById(Iterable<Category> categories) {
        categoryRepository.deleteAll(categories);
    }
}
