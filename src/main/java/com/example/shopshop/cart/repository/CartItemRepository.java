package com.example.shopshop.cart.repository;

import com.example.shopshop.cart.domain.Cart;
import com.example.shopshop.cart.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCartIdAndItemId(Long cartId, Long itemId);
}
