package com.example.shopshop.orders.dto;

import com.example.shopshop.Item.domain.ItemImage;
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
public class OrdersRegisterDTO {

    private Long id;

    @Builder.Default
    private List<OrdersItemDTO> ordersItem = new ArrayList<>();


    private Long cartId;

    private Long memberId;

    private int grandTotal;

    private Delivery delivery;
}
