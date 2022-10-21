package com.example.shopshop.Item.domain;

import com.example.shopshop.etc.BaseEntity;
import com.example.shopshop.member.domain.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Item extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;

    private Integer price;

    private Integer quantity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Member provider;

    private int reviewCnt;
    //    private ItemImage itemImage;


}
