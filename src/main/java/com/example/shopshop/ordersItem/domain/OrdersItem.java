package com.example.shopshop.ordersItem.domain;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.etc.BaseEntity;
import com.example.shopshop.orders.domain.Orders;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = {"orders", "item"})
public class OrdersItem extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private int orderPrice;
    private int count;



}
