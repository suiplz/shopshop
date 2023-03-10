package com.example.shopshop.cart.service;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.Item.dto.ItemDTO;
import com.example.shopshop.Item.service.ItemService;
import com.example.shopshop.cart.domain.CartItem;
import com.example.shopshop.cart.dto.CartDTO;
import com.example.shopshop.cart.dto.CartItemDTO;
import com.example.shopshop.cart.dto.CartItemListDTO;
import com.example.shopshop.cart.dto.CartItemModifyDTO;
import com.example.shopshop.cart.repository.CartItemRepository;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.member.dto.MemberDTO;
import com.example.shopshop.member.service.MemberService;
import com.example.shopshop.page.dto.PageRequestDTO;
import com.example.shopshop.page.dto.PageResultDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    CartItemRepository cartItemRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ItemService itemService;


    @Test
    void cartRegisterTest() throws Exception{

        MemberDTO memberDTO = memberService.get(2L);
        Member member = Member.builder()
                .id(memberDTO.getId())
                .email(memberDTO.getEmail())
                .password(memberDTO.getPassword())
                .address(memberDTO.getAddress())
                .phone(memberDTO.getPhone())
                .name(memberDTO.getName())
                .build();
//
//        ItemDTO itemDTO = itemService.getItem(1L);
//        itemService.dtoToEntity(itemDTO);
//        Item item = Item.builder()
//                .id(itemDTO.getId())
//                .itemName(itemDTO.getItemName())
//                .sizeL(itemDTO.getSizeL())
//                .sizeM(itemDTO.getSizeM())
//                .sizeS(itemDTO.getSizeS())
//                .provider(itemDTO.getProvider())
//                .saleRate(itemDTO.getSaleRate())
//                .price(itemDTO.getPrice())
//                .build();


        CartItemDTO cartItemDTO = CartItemDTO.builder()
//                .itemId(1L)
                .amount(1)
                .size("S")
                .build();

        cartService.register(member,1L, cartItemDTO);



    }

    @Test
    void cartModifyTest() throws Exception{

        CartItemModifyDTO cartItemModifyDTO = CartItemModifyDTO.builder()
                .id(6L)
                .size("S")
                .amount(10)
                .build();

        cartService.modify(cartItemModifyDTO.getId(), cartItemModifyDTO);
    }

    @Test
    @Transactional
    void getCartByMemberIdTest() {

        MemberDTO memberDTO = memberService.get(2L);
        Member member = Member.builder()
                .id(memberDTO.getId())
                .build();

        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        PageResultDTO<CartItemListDTO, Object[]> cartByMember = cartService.getCartByMember(pageRequestDTO, member.getId());

        log.info("result : " + cartByMember);
        log.info("result dtoList : " + cartByMember.getDtoList());



    }

    @Test
    void deleteCartByIdTest() {
        cartService.remove(1L);
    }
}