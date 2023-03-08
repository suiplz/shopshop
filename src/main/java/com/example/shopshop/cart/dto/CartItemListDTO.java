package com.example.shopshop.cart.dto;

import com.example.shopshop.Item.domain.ItemImage;
import com.example.shopshop.Item.dto.ItemImageDTO;
import com.example.shopshop.cart.domain.Cart;
import com.example.shopshop.cart.domain.CartItem;
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

    private String size;

    private int amount;

    private int totalPrice;

    private ItemImageDTO itemImage;

    public int totalPrice() {
        return this.itemPrice * this.amount;
    }


}
