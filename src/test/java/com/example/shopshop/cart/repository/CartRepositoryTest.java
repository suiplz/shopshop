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
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor
class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MemberRepository memberRepository;


    @Transactional
    @Commit
    @Test
    void insertDummy() {

        Optional<Member> result1 = memberRepository.findById(2L);
        Optional<Item> result2 = itemRepository.findById(2L);
        if (result1.isPresent() & result2.isPresent()) {
            Member member = result1.get();
            Item item = result2.get();

            CartItem cartItem = CartItem.builder().item(item).totalPrice(400).totalCount(20).build();
            Cart cart = Cart.builder().buyer(member).build();

            cart.setCartItems(cartItem);
            cartRepository.save(cart);

        }
    }

    @Transactional
    @Commit
    @Test
    void insertCart(){

        Optional<Member> result = memberRepository.findById(1L);
        if (result.isPresent()) {

            Member provider = result.get();

            IntStream.rangeClosed(1, 30).forEach(i -> {
                Member buyer = Member.builder()
                        .name("member" + i)
                        .password("1111")
                        .email("member" + i + "@.com")
                        .build();
                memberRepository.save(buyer);
                Item item = Item.builder()
                        .itemName("itemName.." + i)
                        .price(10 * i)
                        .quantity(3 * i)
                        .provider(provider)
                        .build();
                itemRepository.save(item);
                CartItem cartItem = CartItem.builder()
                        .item(item)
                        .totalPrice(item.getPrice())
                        .totalCount(1)
                        .build();
                Cart cart = Cart.builder()
                        .buyer(buyer)
                        .build();
                cart.setCartItems(cartItem);
                cartRepository.save(cart);

            });
        }


    }

    @Test
    @Transactional
    void getCartByMemberTest() {

        List<Object[]> result = cartRepository.getCartByMemberId(2L);

        for (Object[] objects : result) {
            System.out.println(" result = " + Arrays.toString(objects));
        }
    }

}