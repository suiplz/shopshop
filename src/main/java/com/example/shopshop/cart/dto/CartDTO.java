package com.example.shopshop.cart.dto;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.cart.domain.CartItem;
import com.example.shopshop.member.domain.Member;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    private Long id;

    @Builder.Default
    private List<CartItemDTO> cartItems = new ArrayList<>();

    private Member buyer;

}
