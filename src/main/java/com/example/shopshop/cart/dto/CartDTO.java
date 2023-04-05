package com.example.shopshop.cart.dto;

import com.example.shopshop.Item.domain.ItemImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    private Long id;

    @Builder.Default
    private List<CartItemListDTO> cartItemListDTOS = new ArrayList<>();

//    private Item item;

    private ItemImage itemImage;

    private Long memberId;


}
