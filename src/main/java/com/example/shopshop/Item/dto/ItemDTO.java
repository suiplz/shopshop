package com.example.shopshop.Item.dto;

import com.example.shopshop.member.domain.Member;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemDTO {

    private Long id;

    private String itemName;

    private Integer price;

    private Integer quantity;

    private Member provider;

    @Builder.Default
    private List<ItemImageDTO> imageDTOList = new ArrayList<>();
}
