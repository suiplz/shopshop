package com.example.shopshop.orders.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class OrdersServiceTest {

    @Autowired
    private OrdersService ordersService;

    @Test
    void insertOrders() {


    }

    @Test
    void readOrdersInfoByMemberId() {

        List<Object[]> listByMember = ordersService.getListByMember(1L);
        for (Object[] objects : listByMember) {
            System.out.println("objects = " + Arrays.toString(objects));
        }
    }

    @Test
    void insertOrdersTest() {

        ordersService.register(2L);
        
    }

    @Test
    void cancelOrdersTest() {
        ordersService.cancel(15L);
        ordersService.cancel(16L);
        ordersService.cancel(17L);


    }

}