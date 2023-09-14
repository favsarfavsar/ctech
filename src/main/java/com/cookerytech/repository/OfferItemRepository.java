package com.cookerytech.repository;

import com.cookerytech.domain.OfferItem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface OfferItemRepository extends JpaRepository<OfferItem, Long> {


    @EntityGraph(attributePaths = {"offerId"})
    List<OfferItem> findByOfferId(Long offerId);

    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN true ELSE false END FROM OfferItem o WHERE o.product.id = :productId")
    Boolean existsByProductId(@Param("productId") Long productId);
}
