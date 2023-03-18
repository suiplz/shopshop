package com.example.shopshop.cart.repository;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.Item.repository.ItemRepository;
import com.example.shopshop.cart.domain.Cart;
import com.example.shopshop.cart.domain.CartItem;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.member.domain.MemberRole;
import com.example.shopshop.member.repository.MemberRepository;
import com.example.shopshop.page.dto.PageRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@SpringBootTest
@RequiredArgsConstructor
@Log4j2
class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;


    @Test
    void getCartItemByMemberId() {

        PageRequestDTO requestDTO = new PageRequestDTO();
        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());

        Page<Object[]> cartByMemberId = cartRepository.getCartByMemberId(pageable, 1L);

        for (Object[] objects : cartByMemberId) {
            log.info("result : " + Arrays.toString(objects));
        }
//[Item(id=3, itemName=zxc, price=12, sizeS=1, sizeM=1, sizeL=1, clothType=null, season=null, gender=null, saleRate=2, provider=null), ItemImage(id=6, uuid=bcae3147-b4d8-4e8b-9ec1-2c7d9147510f, imgName=dmitry-ganin-_Qoa_q8C66o-unsplash.jpg, path=2023\03\01), 0.0, 0]
//[Item(id=2, itemName=qwetfqe, price=122, sizeS=33, sizeM=44, sizeL=55, clothType=null, season=null, gender=null, saleRate=2, provider=null), ItemImage(id=4, uuid=aeae5915-42ca-4a14-8805-08836e8b4d66, imgName=maxim-shklyaev-ENcj1kd8yx8-unsplash.jpg, path=2023\03\01), 0.0, 0]
//[Item(id=1, itemName=das, price=1235, sizeS=12, sizeM=2, sizeL=4, clothType=null, season=null, gender=null, saleRate=1, provider=null), ItemImage(id=1, uuid=492867ef-50b0-4729-b105-1d0dfee93ab0, imgName=3ca.PNG, path=2023\01\10), 1.5455, 11]
    }


    @Test
    void getTotalPriceByMemberId() {
        Integer totalPriceByMemberId = cartRepository.getGrandTotalByMemberId(1L);
        System.out.println("totalPriceByMemberId = " + totalPriceByMemberId);
    }


    @Test
    void cartSizeTest() {

        boolean s = cartItemRepository.itemInCart(1L, 1L, "M");
        log.info("result = " + s);
    }

    @Test
    void findCartItemByComp() {

        CartItem cartItem = cartItemRepository.findCartItemByComp(1L, 1L, "M");
        log.info("result : " + cartItem);

    }

    @Test
    void findByCartIdTest() {

        List<Object[]> cartByCartId = cartRepository.findCartByCartId(2L);

        for (Object[] objects : cartByCartId) {
            log.info("result : " + Arrays.toString(objects));
        }

    }

    @Test
    @Transactional
    void getTotalPrice() {
        Optional<CartItem> result = cartItemRepository.findById(5L);
        CartItem cartItem = result.get();
        log.info("result : " + cartItem.getAmount() * cartItem.getItem().getPrice());
    }
}