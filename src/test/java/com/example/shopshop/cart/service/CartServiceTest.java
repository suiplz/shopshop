package com.example.shopshop.cart.service;

import com.example.shopshop.Item.service.ItemService;
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
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Log4j2
class CartServiceTest {

    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private MemberService memberService;

    @Autowired
    private ItemService itemService;


    @Test
    void cartRegisterTest() throws Exception {

        MemberDTO memberDTO = memberService.get(2L);
        Member member = Member.builder()
                .id(memberDTO.getId())
                .email(memberDTO.getEmail())
                .password(memberDTO.getPassword())
                .address1(memberDTO.getAddress1())
                .address2(memberDTO.getAddress2())
                .address3(memberDTO.getAddress3())
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

        cartService.register(member, 1L, cartItemDTO);


    }

    @Test
    void cartModifyTest() throws Exception {

        CartItemModifyDTO cartItemModifyDTO = CartItemModifyDTO.builder()
                .id(9L)
                .size("L")
                .amount(4)
                .build();

        cartService.modify(cartItemModifyDTO.getId(), cartItemModifyDTO);
    }

    @Test
    @Transactional
    void getCartByMemberIdTest() {

        MemberDTO memberDTO = memberService.get(1L);
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

    @Test
    @Transactional
    void getTotal() {

        MemberDTO memberDTO = memberService.get(1L);
        Member member = Member.builder()
                .id(memberDTO.getId())
                .build();

        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        PageResultDTO<CartItemListDTO, Object[]> cartByMember = cartService.getCartByMember(pageRequestDTO, member.getId());
        int i = cartService.grandTotalOfCart(cartByMember.getDtoList());
        log.info("total Price : " + i);

    }
}