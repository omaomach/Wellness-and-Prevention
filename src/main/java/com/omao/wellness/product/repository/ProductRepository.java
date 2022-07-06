package com.omao.wellness.product.repository;

import com.omao.wellness.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductName(String name);

    Page<Product> findByProductName(String name, Pageable pageable);

    Page<Product> findByProductDescription(String description, Pageable pageable);

    Page<Product> findBySuggestedUse(String use, Pageable pageable);


}
