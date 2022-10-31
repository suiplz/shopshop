package com.example.shopshop;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.Item.repository.ItemRepository;
import com.example.shopshop.cart.domain.Cart;
import com.example.shopshop.cart.domain.CartItem;
import com.example.shopshop.cart.repository.CartRepository;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.member.domain.MemberRole;
import com.example.shopshop.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@RequiredArgsConstructor
@Log4j2
public class DataInsert {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MemberRepository memberRepository;


    @Test
    void insertDummy() {

        Member provider = Member.builder().memberRole(MemberRole.PROVIDER).name("pvd").password("1234").email("pvd@asd").build();
        memberRepository.save(provider);
    }

    @Transactional
    @Commit
    @Test
    void insertCart() {

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
}
