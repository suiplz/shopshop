package com.example.shopshop.orders.service;

import com.example.shopshop.orders.dto.OrdersItemListDTO;
import com.example.shopshop.page.dto.PageRequestDTO;
import com.example.shopshop.page.dto.PageResultDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
class OrdersItemServiceTest {

    @Autowired
    private OrdersItemService ordersItemService;

    @Test
    void insertOrders() {


    }

    @Test
    void readOrdersInfoByMemberId() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<OrdersItemListDTO, Object[]> listByMember = ordersItemService.getOrdersByMember(pageRequestDTO, 1L);

        log.info("result : " + listByMember);
    }

    @Test
    void getManagePageByProviderIdTest() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<OrdersItemListDTO, Object[]> listByMember = ordersItemService.getOrdersByProvider(pageRequestDTO, 2L);

        log.info("result : " + listByMember);
    }

//    @Test
//    void insertOrdersTest() {
//
//        ordersItemService.register(2L);
//
//    }

//    @Test
//    void cancelOrdersTest() {
//        ordersItemService.cancel(22L);
//        ordersItemService.cancel(23L);
//
//
//    }

}