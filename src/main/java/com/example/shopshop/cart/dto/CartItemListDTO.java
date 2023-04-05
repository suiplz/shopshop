package com.example.shopshop.cart.dto;

import com.example.shopshop.Item.dto.ItemImageDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemListDTO {

    private Long id;

    private Long cartId;

    private Long itemId;

    private String itemName;

    private int itemPrice;

    private int salePrice;

    private String size;

    private int amount;

    private int totalPrice;

    private ItemImageDTO itemImage;

    public int totalPrice() {
        return this.salePrice * this.amount;
    }


}
