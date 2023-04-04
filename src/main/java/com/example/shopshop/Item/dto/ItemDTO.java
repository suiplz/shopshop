package com.example.shopshop.Item.dto;

import com.example.shopshop.Item.domain.ClothType;
import com.example.shopshop.Item.domain.Gender;
import com.example.shopshop.Item.domain.Season;
import com.example.shopshop.category.domain.Category;
import com.example.shopshop.member.domain.Member;
import groovy.transform.Sortable;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private Long id;

    @NotBlank
    private String itemName;

    private Integer price;

    private Integer sizeS;

    private Integer sizeM;

    private Integer sizeL;

//    private ClothType clothType;
//
//    private Season season;
//
//    private Gender gender;

    private Category category;

    private Integer saleRate;

    private Integer salePrice;

    private Member provider;

    private double avgRate;

    private int reviewCnt;

    private int likesCnt;

    @Builder.Default
    private List<ItemImageDTO> imageDTOList = new ArrayList<>();

    private LocalDateTime regDate;

    private LocalDateTime modDate;


}
