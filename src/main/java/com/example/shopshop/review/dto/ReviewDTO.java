package com.example.shopshop.review.dto;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.member.domain.Member;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewDTO {

    private Long id;

    private String title;

    private String text;

    private Integer rate;

    private Item item;

    private Member member;
}
