package com.example.shopshop.cart.service;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.Item.dto.ItemDTO;
import com.example.shopshop.Item.service.ItemService;
import com.example.shopshop.cart.domain.CartItem;
import com.example.shopshop.cart.dto.CartDTO;
import com.example.shopshop.cart.dto.CartItemDTO;
import com.example.shopshop.cart.dto.CartItemModifyDTO;
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
import java.util.Arrays;
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
    void cartRegisterTest() throws Exception{

        MemberDTO memberDTO = memberService.get(1L);
        Member member = Member.builder()
                .id(memberDTO.getId())
                .email(memberDTO.getEmail())
                .password(memberDTO.getPassword())
                .address(memberDTO.getAddress())
                .phone(memberDTO.getPhone())
                .name(memberDTO.getName())
                .build();

        ItemDTO itemDTO = itemService.getItem(2L);
        itemService.dtoToEntity(itemDTO);
        Item item = Item.builder()
                .id(itemDTO.getId())
                .itemName(itemDTO.getItemName())
                .sizeL(itemDTO.getSizeL())
                .sizeM(itemDTO.getSizeM())
                .sizeS(itemDTO.getSizeS())
                .provider(itemDTO.getProvider())
                .saleRate(itemDTO.getSaleRate())
                .price(itemDTO.getPrice())
                .build();


        cartService.register(member, item, "S", 1);


    }

    @Test
    void cartModifyTest() throws Exception{

        CartItemModifyDTO cartItemModifyDTO = CartItemModifyDTO.builder()
                .id(5L)
                .size("S")
                .amount(1)
                .build();

        // item 재고 수량 변화 itemRepository save? querydsl?

        cartService.modify(cartItemModifyDTO);
    }

    @Test
    void getCartByMemberIdTest() {

        MemberDTO memberDTO = memberService.get(1L);
        Member member = Member.builder()
                .id(memberDTO.getId())
                .build();

        List<Object[]> cartByMember = cartService.getCartByMember(member.getId());

        for (Object[] objects : cartByMember) {
            System.out.println("objects = " + Arrays.toString(objects));
        }
    }

    @Test
    void deleteCartByIdTest() {
        cartService.remove(1L, 1L);
        cartService.remove(1L, 2L);
    }
}