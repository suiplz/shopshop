package com.example.shopshop.orders.dto;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.orders.domain.Orders;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrdersItemDTO {

    private Long id;

    private Orders orders;

    private Item item;

    private int ordersPrice;

    private int ordersCount;

}
