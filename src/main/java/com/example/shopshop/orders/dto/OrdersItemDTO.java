package com.example.shopshop.orders.dto;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.Item.domain.ItemImage;
import com.example.shopshop.Item.dto.ItemImageDTO;
import com.example.shopshop.orders.domain.Orders;
import com.example.shopshop.orders.domain.OrdersStatus;
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

    private Long itemId;

    private String itemName;

    private int itemPrice;

    private String size;

    private int amount;

    private int totalPrice;

    private ItemImageDTO itemImage;

    private OrdersStatus ordersStatus;

}
