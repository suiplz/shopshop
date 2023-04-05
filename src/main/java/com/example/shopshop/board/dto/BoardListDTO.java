package com.example.shopshop.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardListDTO {

    private Long id;

    private String title;

    private Long itemId;

    private Long memberId;

    private String memberEmail;

    private Long commentCount;

}
