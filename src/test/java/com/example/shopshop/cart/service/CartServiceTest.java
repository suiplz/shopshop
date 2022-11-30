package com.example.shopshop.cart.service;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.Item.dto.ItemDTO;
import com.example.shopshop.Item.service.ItemService;
import com.example.shopshop.cart.domain.CartItem;
import com.example.shopshop.cart.dto.CartDTO;
import com.example.shopshop.cart.dto.CartItemDTO;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.member.dto.MemberDTO;
import com.example.shopshop.member.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class CartServiceTest {

    @Autowired
    private CartService cartService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ItemService itemService;

    @Test
    @Transactional
    @Commit
    void cartRegisterTest() {

        MemberDTO memberDTO = memberService.get(1L);
        ItemDTO itemDTO = itemService.getItem(1L);

        Member member = Member.builder()
                .id(memberDTO.getId())
                .name(memberDTO.getName())
                .address(memberDTO.getAddress())
                .phone(memberDTO.getPhone())
                .password(memberDTO.getPassword())
                .email(memberDTO.getEmail())
                .build();

        Item item = Item.builder()
                .id(itemDTO.getId())
                .price(itemDTO.getPrice())
                .itemName(itemDTO.getItemName())
                .id(itemDTO.getId())
                .provider(itemDTO.getProvider())
                .saleRate(itemDTO.getSaleRate())
                .sizeS(itemDTO.getSizeS())
                .sizeM(itemDTO.getSizeM())
                .sizeL(itemDTO.getSizeL())
                .build();

        CartItemDTO cartItem1 = CartItemDTO.builder()
                .id(1L)
                .item(item)
                .amount(1)
                .price(item.getPrice())
                .size("S")
                .build();

        CartItemDTO cartItem2 = CartItemDTO.builder()
                .id(2L)
                .item(item)
                .amount(2)
                .price(item.getPrice())
                .size("M")
                .build();

        List<CartItemDTO> cartItems = new ArrayList<>();

        cartItems.add(cartItem1);
        cartItems.add(cartItem2);

        CartDTO cartDTO = CartDTO.builder()
                .id(1L)
                .cartItems(cartItems)
                .buyer(member)
                .build();

        log.info("cartDTO : " + cartDTO);
        cartService.register(cartDTO);

    }

}