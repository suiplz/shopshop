package com.example.shopshop.orders.repository;

import com.example.shopshop.member.domain.Member;
import com.example.shopshop.orders.domain.Orders;
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

        IntStream.rangeClosed(0, 300).forEach(i -> {

            Member buyer = Member.builder().id((long) i / 10 + 1).build();
            Orders orders = Orders.builder()
                    .buyer(buyer)
                    .build();

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