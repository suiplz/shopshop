package com.example.shopshop.cart.domain;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.etc.BaseEntity;
import com.example.shopshop.member.domain.Member;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "buyer")
public class Cart extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
//    @Builder.Default
//    private List<CartItem> cartItems = new ArrayList<>();


    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member buyer;

//    public void setCartItems(CartItem cartItem) {
//        this.cartItems.add(cartItem);
//        cartItem.setCart(this);
//    }
}
