package com.example.shopshop.board.dto;

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

    private Long itemId;

    private Long memberId;
}
