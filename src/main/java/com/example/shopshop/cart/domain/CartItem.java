package com.example.shopshop.cart.domain;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.etc.BaseEntity;
import com.example.shopshop.orders.domain.Orders;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"cart", "item"})
public class CartItem extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private String size;

    private Integer amount;



    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void changeSize(String size) {
        this.size = size;
    }

    public void changeAmount(Integer amount) {
        this.amount = amount;
    }

}
