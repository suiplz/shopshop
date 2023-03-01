package com.example.shopshop.cart.service;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.cart.domain.Cart;
import com.example.shopshop.cart.domain.CartItem;
import com.example.shopshop.cart.dto.CartDTO;
import com.example.shopshop.cart.dto.CartItemDTO;
import com.example.shopshop.cart.dto.CartItemModifyDTO;
import com.example.shopshop.member.domain.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface CartService {

    void register(Member member, Long ItemId, CartItemDTO cartItemDTO) throws Exception;

//    void register(Member member, CartItemDTO cartItemDTO) throws Exception;

    List<Object[]> getCartByMember(Long id);

    Cart findByMemberId(Long memberId);

    void modify(CartItemModifyDTO cartModifyDTO) throws Exception;


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
                        .item(Item.builder().id(cartItemDTO.getItemId()).build())
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

    void remove(Long cartId, Long itemId);
}
