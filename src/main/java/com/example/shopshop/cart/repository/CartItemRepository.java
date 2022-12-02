package com.example.shopshop.cart.repository;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.cart.domain.Cart;
import com.example.shopshop.cart.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCartIdAndItemId(Long cartId, Long itemId);

    @Transactional
    @Modifying
    @Query("UPDATE CartItem ci set ci.size = :size, ci.amount = :amount WHERE ci.id = :id")
    void updateCartItem(@Param("id") Long id,@Param("size") String size, @Param("amount") int amount);
}
