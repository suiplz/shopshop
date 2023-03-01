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
    void getCartItemByMemberId() {
        List<Object[]> cartByMemberId = cartRepository.getCartByMemberId(1L);

        for (Object[] objects : cartByMemberId) {
            System.out.println("results = " + Arrays.toString(objects));
        }

    }

//    [Item(id=3, itemName=zxc, price=12, sizeS=1, sizeM=1, sizeL=1, clothType=null, season=null, gender=null, saleRate=2, provider=null), ItemImage(id=6, uuid=bcae3147-b4d8-4e8b-9ec1-2c7d9147510f, imgName=dmitry-ganin-_Qoa_q8C66o-unsplash.jpg, path=2023\03\01), 0.0, 0]
//[Item(id=2, itemName=qwetfqe, price=122, sizeS=33, sizeM=44, sizeL=55, clothType=null, season=null, gender=null, saleRate=2, provider=null), ItemImage(id=4, uuid=aeae5915-42ca-4a14-8805-08836e8b4d66, imgName=maxim-shklyaev-ENcj1kd8yx8-unsplash.jpg, path=2023\03\01), 0.0, 0]
//[Item(id=1, itemName=das, price=1235, sizeS=12, sizeM=2, sizeL=4, clothType=null, season=null, gender=null, saleRate=1, provider=null), ItemImage(id=1, uuid=492867ef-50b0-4729-b105-1d0dfee93ab0, imgName=3ca.PNG, path=2023\01\10), 1.5455, 11]

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