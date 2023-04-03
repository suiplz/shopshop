package com.example.shopshop.orders.dto;

import com.example.shopshop.Item.dto.ItemImageDTO;
import com.example.shopshop.orders.domain.OrdersStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersItemListDTO {

    private Long id;


    private int ordersPrice;

    private int ordersCount;

    private String size;

    private int totalPrice;

    private Long itemId;

    private String itemName;

    private ItemImageDTO itemImage;

    private LocalDateTime regDate;

    private OrdersStatus ordersStatus;

}
