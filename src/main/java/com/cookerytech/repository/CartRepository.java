package com.cookerytech.repository;

import com.cookerytech.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

//    @Query("SELECT c FROM Cart c JOIN c.user u WHERE u.id = :id")
//    Cart getCartsWithUserId(@Param("id") Long id);

    @Query("SELECT c FROM Cart c")
    List<Cart> findAllCarts();
}
