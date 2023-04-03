package com.example.shopshop.review.dto;

import com.example.shopshop.Item.dto.ItemImageDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewListDTO {

    private Long reviewId;

    private String title;

    private String text;

    private Integer grade;

    private Long itemId;

    private String itemName;

    private ItemImageDTO itemImage;
}
