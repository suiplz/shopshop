package com.example.shopshop.orders.service;

import com.example.shopshop.orders.domain.Orders;
import com.example.shopshop.orders.domain.OrdersItem;
import com.example.shopshop.orders.dto.OrdersDTO;
import com.example.shopshop.orders.dto.OrdersItemDTO;

import java.util.List;

public interface OrdersService {

    Long register(OrdersDTO ordersDTO, OrdersItemDTO ordersItemDTO);

    List<Object[]> getListByMember(Long id);

    void cancel(Long id);


    default Orders dtoToOrders(OrdersDTO dto) {

        Orders orders = Orders.builder()
                .buyer(dto.getBuyer())
                .delivery(dto.getDelivery())
                .build();

        return orders;
    }

    default OrdersItem dtoToOrdersItem(OrdersItemDTO dto) {

        OrdersItem ordersItem = OrdersItem.builder()
                .orders(dto.getOrders())
                .item(dto.getItem())
                .ordersCount(dto.getOrdersPrice())
                .ordersCount(dto.getOrdersCount())
                .build();

        return ordersItem;

    }


}
