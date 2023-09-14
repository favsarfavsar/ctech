package com.cookerytech.repository;

import com.cookerytech.domain.Cart_Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<Cart_Items,Long> {
    @Query("SELECT ci FROM Cart_Items ci JOIN ci.productId pr WHERE pr.id = :productId")
    List<Cart_Items> getCartItemsByProductId(@Param("productId") Long productId);

}
