package com.example.shopshop.cart.service;

import com.example.shopshop.cart.domain.Cart;
import com.example.shopshop.cart.domain.CartItem;
import com.example.shopshop.cart.dto.CartDTO;
import com.example.shopshop.cart.dto.CartItemDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface CartService {

    Long register(CartDTO dto);

    List<Object[]> getCartByMember(Long id);


    default Map<String, Object> dtoToEntity(CartDTO cartDTO) {
        Map<String, Object> entityMap = new HashMap<>();

        Cart cart = Cart.builder()
                .buyer(cartDTO.getBuyer())
                .build();
        entityMap.put("cart", cart);

        List<CartItemDTO> cartItemDTOList = cartDTO.getCartItems();

        if (cartItemDTOList != null && cartItemDTOList.size() > 0 ) {
            List<CartItem> cartItemList = cartItemDTOList.stream().map(cartItemDTO -> {

                CartItem cartItem = CartItem.builder()
                        .item(cartItemDTO.getItem())
                        .price(cartItemDTO.getPrice())
                        .amount(cartItemDTO.getAmount())
                        .size(cartItemDTO.getSize())
                        .cart(cart)
                        .build();
                return cartItem;
            }).collect(Collectors.toList());

            entityMap.put("cartItemList", cartItemList);
        }

        return entityMap;
    }
}
