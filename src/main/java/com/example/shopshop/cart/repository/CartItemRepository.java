package com.example.shopshop.cart.repository;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.cart.domain.Cart;
import com.example.shopshop.cart.domain.CartItem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {


    @Query("SELECT CASE WHEN COUNT(ci) > 0 THEN true ELSE false END " +
            "FROM CartItem ci " +
            "WHERE ci.cart.id = :cartId AND ci.item.id = :itemId AND ci.size = :dtoSize")
    boolean itemInCart(@Param("cartId") Long cartId, @Param("itemId") Long itemId, @Param("dtoSize") String dtoSize);

    @Query("SELECT ci " +
            "FROM CartItem ci " +
            "WHERE ci.cart.id = :cartId AND ci.item.id = :itemId AND ci.size = :dtoSize")
    CartItem findCartItemByComp(@Param("cartId") Long cartId, @Param("itemId") Long itemId, @Param("dtoSize") String dtoSize);



    @Transactional
    @Modifying
    @Query("UPDATE CartItem ci set ci.size = :size, ci.amount = :amount WHERE ci.id = :id")
    void updateCartItem(@Param("id") Long id, @Param("size") String size, @Param("amount") int amount);

}
