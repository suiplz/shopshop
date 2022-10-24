package com.example.shopshop.orders.dto;

import com.example.shopshop.delivery.domain.Delivery;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.orders.domain.Orders;
import com.example.shopshop.orders.domain.OrdersItem;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrdersDTO {

    private Long id;

    private List<OrdersItem> ordersItem;

    private Member buyer;

    private Delivery delivery;
}
