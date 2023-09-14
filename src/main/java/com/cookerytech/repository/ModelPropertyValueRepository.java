package com.cookerytech.repository;

import com.cookerytech.domain.ModelPropertyValue;
import com.cookerytech.domain.ProductPropertyKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelPropertyValueRepository extends JpaRepository<ModelPropertyValue, Long> {
    boolean existsByProductPropertyKey(ProductPropertyKey productPropertyKey);//A10
}
