package com.example.shopshop.orders.domain;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.etc.BaseEntity;
import com.example.shopshop.member.domain.Member;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = {"member", "item"})
public class OrdersHistory extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int ordersPrice;

    private int ordersCount;

    private String size;

    private int totalPrice;

    @Enumerated(EnumType.STRING)
    private OrdersStatus ordersStatus;


    public void cancelRequestOrdersStatus() {
        this.ordersStatus = OrdersStatus.취소요청;
    }
    public void changeOrdersStatus(OrdersStatus ordersStatus) {
        this.ordersStatus = ordersStatus;
    }



}
