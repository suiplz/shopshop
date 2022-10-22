package com.example.shopshop.orders.domain;

import com.example.shopshop.delivery.domain.Delivery;
import com.example.shopshop.etc.BaseEntity;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.ordersItem.domain.OrdersItem;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = "buyer")
public class Orders extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrdersItem> ordersItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member buyer;

    @OneToOne
    private Delivery delivery;

    public void addOrdersItem(OrdersItem ordersItem) {
        this.ordersItem.add(ordersItem);
    }



}
