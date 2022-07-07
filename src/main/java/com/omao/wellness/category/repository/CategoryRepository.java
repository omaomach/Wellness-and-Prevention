package com.omao.wellness.category.repository;

import com.omao.wellness.category.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Page<Category> findByCategoryName(String name,Pageable pageable);
    @Override
    Page<Category> findAll(Pageable pageable);


    Page<Category> findByDescriptionContainsIgnoreCase(String term,Pageable pageable);

}
