package com.example.shopshop.cart.repository;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.Item.repository.ItemRepository;
import com.example.shopshop.cart.domain.Cart;
import com.example.shopshop.cart.domain.CartItem;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.member.domain.MemberRole;
import com.example.shopshop.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;


@SpringBootTest
@RequiredArgsConstructor
class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;


    @Test
    void getCartByMemberId() {

        Cart cart = cartRepository.findByBuyerId(1L);
        System.out.println("cart = " + cart);
    }

    @Test
    void findCartItemByMemberId() {
        List<Object[]> cartByMemberId = cartRepository.getCartByMemberId(1L);

        for (Object[] objects : cartByMemberId) {
            System.out.println("results = " + Arrays.toString(objects));
        }

    }

//    @Test
//    void isAlreadyInCartTest(){
//        Boolean alreadyInCart = cartRepository.isAlreadyInCart(1L, 1L);
//        System.out.println("alreadyInCart = " + alreadyInCart);
//    }

    @Test
    void getTotalPriceByMemberId() {
        Integer totalPriceByMemberId = cartRepository.getTotalPriceByMemberId(1L);
        System.out.println("totalPriceByMemberId = " + totalPriceByMemberId);
    }
}