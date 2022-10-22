package com.example.shopshop.orders.repository;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.orders.domain.Orders;
import com.example.shopshop.ordersItem.domain.OrdersItem;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
class OrdersRepositoryTest {

    @Autowired
    private OrdersRepository ordersRepository;

    @Test
    void insertOrders(){

        Member provider = Member.builder().id(1L).build();
        IntStream.rangeClosed(0, 20).forEach(i -> {

            Member buyer = Member.builder().id((long) i).build();
            Item item = Item.builder().itemName("item" + i).provider(provider).price(1000).quantity(1000).build();

            OrdersItem ordersItem = OrdersItem.builder().item(item).ordersPrice(item.getPrice()).ordersCount(3).build();

            Orders orders = Orders.builder()
                    .buyer(buyer)
                    .build();
            orders.addOrdersItem(ordersItem);

            ordersRepository.save(orders);
        });
    }

    @Test
    void getOrdersByMember() {

        List<Orders> ordersByMemberId = ordersRepository.getOrdersByMemberId(1L);
        for (Orders orders : ordersByMemberId) {
            log.info("orders = " + orders);
        }
    }

}