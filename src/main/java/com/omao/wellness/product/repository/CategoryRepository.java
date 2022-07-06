package com.omao.wellness.product.repository;

import com.omao.wellness.product.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Page<Category> findByCategoryName(String name,Pageable pageable);
    @Override
    Page<Category> findAll(Pageable pageable);


    Page<Category> findByDescriptionContainsIgnoreCase(String term,Pageable pageable);

}
