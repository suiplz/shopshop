package com.example.shopshop.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDetailDTO {

    private Long cartItemId;

    private String itemName;

    private int amount;

    private int size;

    private String itemImage;

    private LocalDateTime regDate;

    private LocalDateTime modDate;
}
