package com.example.shopshop.Item.dto;

import com.example.shopshop.member.domain.Member;
import lombok.*;

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

    private Integer quantity;

    private Member provider;

    @Builder.Default
    private List<ItemImageDTO> imageDTOList = new ArrayList<>();

    private LocalDateTime regDate;

    private LocalDateTime modDate;
}
