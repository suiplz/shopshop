package com.example.shopshop.board.dto;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {

    private Long id;

    private String title;

    private String content;

    private Item item;

    private Member member;
}
