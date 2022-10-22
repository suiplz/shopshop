package com.example.shopshop.orders.service;

import com.example.shopshop.orders.domain.Orders;
import com.example.shopshop.orders.dto.OrdersDTO;

import java.util.List;

public interface OrdersService {

    Long register(OrdersDTO dto);

    List<Orders> getListByMember(Long id);

    void cancel(Long id);


    default Orders dtoToEntity(OrdersDTO dto) {

        Orders orders = Orders.builder()
                .buyer(dto.getBuyer())
                .ordersItem(dto.getOrdersItem())
                .delivery(dto.getDelivery())
                .build();

        return orders;

    }


}
