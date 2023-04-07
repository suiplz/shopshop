package com.example.shopshop.review.dto;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.member.domain.Member;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewDTO {

    private Long reviewId;

    @NotEmpty(message = "제목을 입력해주세요")
    private String title;

    @NotEmpty(message = "내용을 입력해주세요")
    private String text;

    private Integer grade;

    private Item item;

    private Member member;

    private LocalDateTime regDate, modDate;
}
