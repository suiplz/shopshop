package com.example.shopshop.orders.service;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.orders.dto.OrdersDTO;
import com.example.shopshop.orders.dto.OrdersItemDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrdersServiceTest {

    @Autowired
    private OrdersService ordersService;

    @Test
    void insertOrders() {


    }

    @Test
    void readOrdersInfoByMemberId() {

        List<Object[]> listByMember = ordersService.getListByMember(21L);
        for (Object[] objects : listByMember) {
            System.out.println("objects = " + Arrays.toString(objects));
        }
    }

}