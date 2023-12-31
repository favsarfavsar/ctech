package com.cookerytech.repository;

import com.cookerytech.domain.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    @Query("SELECT m FROM Model m WHERE m.isActive = true")
    Page<Model> getActiveModels(Pageable pageable);

    Boolean existsBySku(String sku);

    @Query("Select m from Model m  where m.product.id=:productId")
    List<Model> findAllByProductId(@Param("productId") Long productId);

}
