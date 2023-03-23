package com.example.shopshop.board.dto;

import com.example.shopshop.Item.dto.ItemImageDTO;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardListDTO {

    private Long id;

    private String title;

    private Long itemId;

    private String memberEmail;

    private Long commentCount;

}
