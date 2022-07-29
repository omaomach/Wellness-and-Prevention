package com.omao.wellness.category.controller;

import com.omao.wellness.category.model.Category;
import com.omao.wellness.category.model.CategoryPost;
import com.omao.wellness.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping(value = "/create", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryPost category) {
        return ResponseEntity.status(CREATED).body(
                categoryService.createCategory(new Category(null,
                        category.getCategoryName(),
                        category.getDescription(),
                        category.getImageUrl())));
    }

    @GetMapping(value = "/{page_number}", produces = "application/json")
    public ResponseEntity<Optional<Page<Category>>> getAllCategories(@PathVariable String page_number) {
        return ResponseEntity.status(OK).body(categoryService.listAllCategories(Integer.parseInt(page_number)));
    }

    @GetMapping(value = "/id/{id}", produces = "application/json")
    public ResponseEntity<Optional<Category>> findCatById(@PathVariable String id) {
        return ResponseEntity.status(OK).body(categoryService.findCategoryById(Long.parseLong(id)));
    }

    @GetMapping(value = "/name/{name}/{page_number}", produces = "application/json")
    public ResponseEntity<Optional<Page<Category>>> findCatByName(@PathVariable String name, @PathVariable String page_number) {
        return ResponseEntity.status(OK).body(categoryService.listCategoryByName(name, Integer.parseInt(page_number) - 1));
    }

    @GetMapping(value = "/search/{term}/{page_number}", produces = "application/json")
    public ResponseEntity<Optional<Page<Category>>> searchCategory(@PathVariable String term, @PathVariable String page_number) {
        return ResponseEntity.status(OK).body(categoryService.findCategoryContainingSearchWord(term, Integer.parseInt(page_number) - 1));
    }

    @PutMapping(value = "/update", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Optional<Category>> updateCategory(@RequestBody Category category) {
        return ResponseEntity.status(ACCEPTED).body(categoryService.update(category));
    }

    @DeleteMapping(value = "/delete", consumes = "application/json")
    public ResponseEntity<?> deleteCategory(@RequestBody Category category) {
        categoryService.deleteCategory(category);
        return ResponseEntity.status(ACCEPTED).body("Category deleted successfully...\uD83D\uDE0A");
    }

    @DeleteMapping(value = "/delete/batch", consumes = "application/json")
    public ResponseEntity<?> deleteCategoriesSelected(@RequestBody Iterable<Category> categories) {
        categoryService.deleteAllCategoriesById(categories);
        return ResponseEntity.status(ACCEPTED).body("Selected categories deleted successfully...\uD83D\uDE0A");
    }

    @DeleteMapping(value = "/delete/all")
    public ResponseEntity<?> deleteAllCategories() {
        categoryService.deleteAllCategories();
        return ResponseEntity.status(ACCEPTED).body("Categories deleted successfully...\uD83D\uDE0A");
    }


}
