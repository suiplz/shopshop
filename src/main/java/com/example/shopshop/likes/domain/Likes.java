package com.example.shopshop.likes.domain;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.etc.BaseEntity;
import com.example.shopshop.member.domain.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"member", "item"})
public class Likes extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

}
