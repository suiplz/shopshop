package com.example.shopshop.Item.dto;

import com.example.shopshop.member.domain.Member;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemModifyDTO {

    private Long id;

    private String itemName;

    private Integer price;

    private Integer quantity;
}
