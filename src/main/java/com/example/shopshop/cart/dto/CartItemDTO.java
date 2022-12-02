package com.example.shopshop.cart.dto;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.cart.domain.Cart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {

    private Long id;

    private Cart cart;

    private Item item;

    private String size;

    private Integer amount;

}
