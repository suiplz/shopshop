package com.example.shopshop.Item.dto;

import com.example.shopshop.Item.domain.ClothType;
import com.example.shopshop.Item.domain.Gender;
import com.example.shopshop.Item.domain.Season;
import com.example.shopshop.category.domain.Category;
import com.example.shopshop.member.domain.Member;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemModifyDTO {

    private Long id;

    private String itemName;

    private Integer price;

    private Integer sizeS;

    private Integer sizeM;

    private Integer sizeL;

    private Integer saleRate;

    private ClothType clothType;

    private Season season;

    private Gender gender;

    private Category category;

    private Member provider;

}
