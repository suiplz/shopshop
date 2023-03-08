package com.example.shopshop.cart.service;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.Item.domain.ItemImage;
import com.example.shopshop.Item.dto.ItemImageDTO;
import com.example.shopshop.cart.domain.Cart;
import com.example.shopshop.cart.domain.CartItem;
import com.example.shopshop.cart.dto.CartDTO;
import com.example.shopshop.cart.dto.CartItemDTO;
import com.example.shopshop.cart.dto.CartItemListDTO;
import com.example.shopshop.cart.dto.CartItemModifyDTO;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.page.dto.PageRequestDTO;
import com.example.shopshop.page.dto.PageResultDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface CartService {

    void register(Member member, Long ItemId, CartItemDTO cartItemDTO) throws Exception;

//    void register(Member member, CartItemDTO cartItemDTO) throws Exception;

    PageResultDTO<CartItemListDTO, Object[]> getCartByMember(PageRequestDTO pageRequestDTO, Long memberId);

    Cart findByMemberId(Long memberId);

    void modify(CartItemModifyDTO cartModifyDTO) throws Exception;


//    default Map<String, Object> dtoToEntity(CartDTO cartDTO) {
//        Map<String, Object> entityMap = new HashMap<>();
//
//        Cart cart = Cart.builder()
//                .buyer(cartDTO.getBuyer())
//                .build();
//        entityMap.put("cart", cart);
//
////        List<CartItemDTO> cartItemDTOList = cartDTO.getCartItemListDTOS();
//
//        if (cartItemDTOList != null && cartItemDTOList.size() > 0 ) {
//            List<CartItem> cartItemList = cartItemDTOList.stream().map(cartItemDTO -> {
//                CartItem cartItem = CartItem.builder()
//                        .item(Item.builder().id(cartItemDTO.getItemId()).build())
//                        .amount(cartItemDTO.getAmount())
//                        .size(cartItemDTO.getSize())
//                        .cart(cart)
//                        .build();
//                return cartItem;
//            }).collect(Collectors.toList());
//
//            entityMap.put("cartItemList", cartItemList);
//        }

//        return entityMap;
//    }


    default CartItemListDTO entitiesToDTO(Cart cart, CartItem cartItem, Long itemId, String itemName, int itemPrice, ItemImage itemImage) {

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
                        .size(cartItem.getSize())
                        .amount(cartItem.getAmount())
                        .totalPrice(cartItem.getAmount() * itemPrice)
                        .itemImage(itemImageDTO)
                        .build();

//        cartDTO.setCartItemListDTOS(cartItemListDTO);

//        cartDTO.setItemImage(itemImage);

        return cartItemListDTO;
        }

    void remove(Long cartItemId);
}
