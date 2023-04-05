package com.example.shopshop.orders.repository;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.Item.domain.ItemImage;
import com.example.shopshop.Item.repository.ItemRepository;
import com.example.shopshop.cart.domain.CartItem;
import com.example.shopshop.cart.repository.CartRepository;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.orders.domain.OrdersItem;
import com.example.shopshop.orders.domain.OrdersStatus;
import com.example.shopshop.page.dto.PageRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
class OrdersRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrdersItemRepository ordersItemRepository;

    @Test
    void insertOrders() {

        Member provider = Member.builder().id(22L).build();
        IntStream.rangeClosed(1, 20).forEach(i -> {

            Member buyer = Member.builder().id((long) i).build();
            Optional<Item> result = itemRepository.findById((long) i);
            Item item = result.get();

            OrdersItem ordersItem = OrdersItem.builder().item(item).ordersPrice(item.getPrice()).ordersCount(3).build();

        });
    }

    @Test
    void findCartByCartIdTest() {
        List<Object[]> result = cartRepository.findCartByCartId(2L);
//        for (Object[] objects : result) {
//            log.info("result : " + Arrays.toString(objects));
//        }

        List<CartItem> cartItemList = new ArrayList<>();
        result.forEach(arr -> {
            log.info("result : " + arr[2]);
            log.info("price : " + arr[5]);
            CartItem cartItem = (CartItem) arr[2];
            log.info("cartItem : " + cartItem);
            log.info("cartItem : " + cartItem);
            cartItemList.add(cartItem);
        });
        log.info("c.id, m.id, ci, i.id, i.itemName, i.price, ii");
        log.info("{}, {}, {}, {}, {}, {}, {}", (Long) result.get(0)[0], (Long) result.get(0)[1], cartItemList, (Long) result.get(0)[3], (String) result.get(0)[4], (int) result.get(0)[5], (ItemImage) result.get(0)[6]);

    }

    @Test
    @Transactional
    void getOrdersByMember() {
        PageRequestDTO requestDTO = new PageRequestDTO();
        Pageable pageable = requestDTO.getPageable(Sort.by("id").ascending());

        Page<Object[]> cartByMemberId = ordersItemRepository.getOrdersByMemberId(pageable, 1L);

        for (Object[] objects : cartByMemberId) {
            log.info("result : " + Arrays.toString(objects));
        }

    }

    @Test
    void getOrdersByProvider() {
        PageRequestDTO requestDTO = new PageRequestDTO();
        Pageable pageable = requestDTO.getPageable(Sort.by("id").ascending());

        Page<Object[]> cartByMemberId = ordersItemRepository.getOrdersByProviderId(pageable, 2L);

        for (Object[] objects : cartByMemberId) {
            log.info("result : " + Arrays.toString(objects));
        }

    }

    @Test
    @Transactional
    void getOrderItemsByMember() {

//        List<OrdersItem> result = ordersRepository.getOrdersItemByMemberId(3L);
//
//        for (OrdersItem ordersItem : result) {
//            log.info("OrdersItem = " + ordersItem);
//        }

        List<Object[]> ordersItemByMemberId = ordersItemRepository.getOrdersItemByMemberId(3L);
        System.out.println("ordersItemByMemberId = " + ordersItemByMemberId);
        for (Object[] objects : ordersItemByMemberId) {
            System.out.println("toString = " + Arrays.toString(objects));
        }

    }

    @Test
    void statusTest() {
        OrdersStatus ordersStatus = OrdersStatus.fromValue("CANCEL");

        Optional<OrdersItem> result = ordersItemRepository.findById(6L);
        OrdersItem ordersItem = result.get();
        log.info("result : " + ordersItem);
        ordersItem.changeOrdersStatus(ordersStatus);

        log.info("result : " + ordersItem);
//
//        log.info("result : " + ordersStatus);
    }

    @Test
    void removeForReset() {
//        ordersRepository.deleteById(1L);
//        ordersRepository.deleteById(2L);
//        ordersRepository.deleteById(3L);


        ordersItemRepository.deleteById(13L);
        ordersItemRepository.deleteById(14L);
        ordersItemRepository.deleteById(15L);
    }


    @Test
    void sumByImpUidTest() {

        int result = ordersItemRepository.sumByImpUid("imp_919712207855");
        log.info("result : " + result);

    }
}