package com.example.shopshop.orders.dto;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.Item.domain.ItemImage;
import com.example.shopshop.Item.dto.ItemImageDTO;
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

    private Long itemId;

    private String itemName;

    private int itemPrice;

    private String size;

    private Integer amount;

    private int totalPrice;

    private ItemImageDTO itemImage;

    private int totalPrice() {
        return this.itemPrice * this.amount;
    }

}
