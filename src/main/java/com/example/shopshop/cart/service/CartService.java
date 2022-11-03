package com.example.shopshop.cart.service;

import com.example.shopshop.cart.domain.Cart;
import com.example.shopshop.cart.dto.CartDTO;

public interface CartService {

    Long Register(CartDTO dto);

    default Cart dtoToEntity(CartDTO cartDTO) {

        Cart cart = Cart.builder()
                .cartItems(cartDTO.getCartItems())
                .buyer(cartDTO.getBuyer())
                .build();

        return cart;
    }
}
