package com.example.shopshop.orders.repository;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.Item.repository.ItemRepository;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.orders.domain.Orders;
import com.example.shopshop.orders.domain.OrdersItem;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
class OrdersRepositoryTest {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void insertOrders(){

        Member provider = Member.builder().id(22L).build();
        IntStream.rangeClosed(1, 20).forEach(i -> {

            Member buyer = Member.builder().id((long) i).build();
            Optional<Item> result = itemRepository.findById((long) i);
            Item item = result.get();

            OrdersItem ordersItem = OrdersItem.builder().item(item).ordersPrice(item.getPrice()).ordersCount(3).build();

            Orders orders = Orders.builder()
                    .buyer(buyer)
                    .build();
            orders.addOrdersItem(ordersItem);


            ordersRepository.save(orders);
        });
    }

    @Test
    @Transactional
    void getOrdersByMember() {

        List<Orders> ordersByMemberId = ordersRepository.getOrdersByMemberId(1L);
        for (Orders orders : ordersByMemberId) {
            log.info("orders = " + orders);
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

        List<Object[]> ordersItemByMemberId = ordersRepository.getOrdersItemByMemberId(3L);
        System.out.println("ordersItemByMemberId = " + ordersItemByMemberId);
        for (Object[] objects : ordersItemByMemberId) {
            System.out.println("toString = " + Arrays.toString(objects));
        }

    }

}