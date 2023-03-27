package com.example.shopshop.orders.service;

import com.example.shopshop.orders.dto.OrdersItemListDTO;
import com.example.shopshop.page.dto.PageRequestDTO;
import com.example.shopshop.page.dto.PageResultDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Log4j2
class OrdersServiceTest {

    @Autowired
    private OrdersService ordersService;

    @Test
    void insertOrders() {


    }

    @Test
    void readOrdersInfoByMemberId() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<OrdersItemListDTO, Object[]> listByMember = ordersService.getOrdersByMember(pageRequestDTO,1L);

        log.info("result : " + listByMember);
    }

    @Test
    void getManagePageByProviderIdTest() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<OrdersItemListDTO, Object[]> listByMember = ordersService.getOrdersByProvider(pageRequestDTO,2L);

        log.info("result : " + listByMember);
    }

    @Test
    void insertOrdersTest() {

        ordersService.register(2L);
        
    }

    @Test
    void cancelOrdersTest() {
        ordersService.cancel(22L);
        ordersService.cancel(23L);


    }

}