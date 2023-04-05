package com.example.shopshop.cart.domain;

import com.example.shopshop.etc.BaseEntity;
import com.example.shopshop.member.domain.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "buyer")
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member buyer;

    private Integer totalPrice;

    public void setTotalPrice(Integer totalPrice) {
        if (this.totalPrice == null) {
            this.totalPrice = 0;
        }
        this.totalPrice = totalPrice;
    }

}
