package com.example.shopshop.review.dto;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.member.domain.Member;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewDTO {

    private Long reviewId;

    private String title;

    private String text;

    private Integer grade;

    private Item item;

    private Member member;

    private LocalDateTime regDate, modDate;
}
