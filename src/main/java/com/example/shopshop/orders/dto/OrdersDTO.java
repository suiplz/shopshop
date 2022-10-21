package com.example.shopshop.orders.dto;

import com.example.shopshop.member.domain.Member;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrdersDTO {

    private Long id;

    private Member buyer;
}
