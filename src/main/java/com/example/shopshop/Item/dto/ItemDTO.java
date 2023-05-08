package com.example.shopshop.Item.dto;

import com.example.shopshop.category.domain.Category;
import com.example.shopshop.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private Long id;

    @NotEmpty(message = "상품명을 입력해주세요.")
    private String itemName;

    @NotNull(message = "가격을 입력해주세요.")
    @Min(value = 1000, message = "가격은 1000원 이상으로 설정할 수 있습니다.")
    @Max(value = 100000000, message = "가격은 1억 이하로 설정할 수 있습니다.")
    private Integer price;

    @NotNull(message = "S 사이즈의 수량을 입력해주세요")
    @Min(value = 0, message = "수량은 0부터 1000까지 가능합니다.")
    @Max(value = 1000, message = "수량은 0부터 1000까지 가능합니다.")
    private Integer sizeS;

    @NotNull(message = "M 사이즈의 수량을 입력해주세요")
    @Min(value = 0, message = "수량은 0부터 1000까지 가능합니다.")
    @Max(value = 1000, message = "수량은 0부터 1000까지 가능합니다.")
    private Integer sizeM;

    @NotNull(message = "L 사이즈의 수량을 입력해주세요")
    @Min(value = 0, message = "수량은 0부터 1000까지 가능합니다.")
    @Max(value = 1000, message = "수량은 0부터 1000까지 가능합니다.")
    private Integer sizeL;

//    private ClothType clothType;
//
//    private Season season;
//
//    private Gender gender;

    private Category category;

    @NotNull(message = "할인율을 입력해주세요.")
    @Min(value = 0, message = "할인율은 0 이상이어야 합니다.")
    @Max(value = 90, message = "할인율은 90 이하이어야 합니다.")
    private Integer saleRate;


    private Integer salePrice;

    private Long providerId;

    private double avgRate;

    private int reviewCnt;

    private int likesCnt;

    @Size(min = 1, max = 10, message = "이미지는 1개에서 10개까지 업로드 할 수 있습니다.")
    @Builder.Default
    private List<ItemImageDTO> imageDTOList = new ArrayList<>();

    private LocalDateTime regDate;

    private LocalDateTime modDate;


}
