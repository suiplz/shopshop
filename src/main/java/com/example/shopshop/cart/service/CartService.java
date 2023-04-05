package com.example.shopshop.cart.service;

import com.example.shopshop.Item.domain.ItemImage;
import com.example.shopshop.Item.dto.ItemImageDTO;
import com.example.shopshop.cart.domain.Cart;
import com.example.shopshop.cart.domain.CartItem;
import com.example.shopshop.cart.dto.CartItemDTO;
import com.example.shopshop.cart.dto.CartItemListDTO;
import com.example.shopshop.cart.dto.CartItemModifyDTO;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.page.dto.PageRequestDTO;
import com.example.shopshop.page.dto.PageResultDTO;

import java.util.List;

public interface CartService {

    void register(Member member, Long ItemId, CartItemDTO cartItemDTO) throws Exception;

    PageResultDTO<CartItemListDTO, Object[]> getCartByMember(PageRequestDTO pageRequestDTO, Long memberId);

    Cart findByMemberId(Long memberId);

    void modify(Long cartItemId, CartItemModifyDTO cartModifyDTO) throws Exception;



    default CartItemListDTO entitiesToDTO(Cart cart, CartItem cartItem, Long itemId, String itemName, int itemPrice, int salePrice, ItemImage itemImage) {

//        CartDTO cartDTO = CartDTO.builder()
//                .id(cart.getId())
//                .memberId(cart.getBuyer().getId())
//                .build();

        ItemImageDTO itemImageDTO = new ItemImageDTO().builder()
                .uuid(itemImage.getUuid())
                .imgName(itemImage.getImgName())
                .path(itemImage.getPath())
                .build();

        CartItemListDTO cartItemListDTO =
                CartItemListDTO.builder()
                        .id(cartItem.getId())
                        .cartId(cart.getId())
                        .itemId(itemId)
                        .itemName(itemName)
                        .itemPrice(itemPrice)
                        .salePrice(salePrice)
                        .size(cartItem.getSize())
                        .amount(cartItem.getAmount())
                        .totalPrice(cartItem.getAmount() * salePrice)
                        .itemImage(itemImageDTO)
                        .build();


        return cartItemListDTO;
    }

    void remove(Long cartItemId);

    int grandTotalOfCart(List<CartItemListDTO> dtoList);
}
