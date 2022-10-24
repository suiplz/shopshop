package com.example.shopshop.orders.domain;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.etc.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = {"orders", "item"})
public class OrdersItem extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id")
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int ordersPrice;

    private int ordersCount;


    public void setOrders(Orders orders) {
        this.orders = orders;
    }

}
