package com.example.shopshop.category.dto;

import com.example.shopshop.category.domain.Season;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDTO {

    private Long id;

    private String name;

    private Season season;
}
