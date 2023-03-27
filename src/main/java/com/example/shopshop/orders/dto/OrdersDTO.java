package com.example.shopshop.orders.dto;

import com.example.shopshop.orders.domain.OrdersStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDTO {

    private Long id;

    private Long memberId;
}
