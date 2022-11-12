package com.example.shopshop.Item.domain;

import com.example.shopshop.category.domain.Category;
import com.example.shopshop.etc.BaseEntity;
import com.example.shopshop.member.domain.Member;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Item extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String itemName;

    @NotNull
    private Integer price;

    @NotNull
    private Integer sizeS;

    @NotNull
    private Integer sizeM;

    @NotNull
    private Integer sizeL;

    @NotNull
    private Integer saleRate;

//    private List<String> imageUrl = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member provider;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "category_id")
//    private Category category;

//    private int reviewCnt;
    //    private ItemImage itemImage;

    public void changeItem(String itemName, Integer price, Integer sizeS, Integer sizeM, Integer sizeL, Integer saleRate) {
        this.itemName = itemName;
        this.price = price;
        this.sizeS = sizeS;
        this.sizeM = sizeM;
        this.sizeL = sizeL;
        this.saleRate = saleRate;
    }



}
