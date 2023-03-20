package com.example.shopshop.orders.dto;

import com.example.shopshop.delivery.domain.Delivery;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrdersListDTO {

//    private Long id;

    @Builder.Default
    private List<OrdersDTO> orders = new ArrayList<>();

    @Builder.Default
    private List<OrdersItemDTO> ordersItem = new ArrayList<>();

    private Long memberId;

    private int grandTotal;

    private Delivery delivery;
}
