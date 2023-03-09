package com.example.shopshop.orders.domain;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.delivery.domain.Delivery;
import com.example.shopshop.etc.BaseEntity;
import com.example.shopshop.member.domain.Member;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @Builder.Default
    private List<OrdersItem> ordersItem = new ArrayList<>();

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member buyer;

    @OneToOne
    private Delivery delivery;


    public void addOrdersItem(OrdersItem ordersItem) {
        this.ordersItem.add(ordersItem);
        ordersItem.setOrders(this);
    }

}
