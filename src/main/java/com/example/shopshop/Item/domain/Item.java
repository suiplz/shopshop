package com.example.shopshop.Item.domain;

import com.example.shopshop.category.domain.Category;
import com.example.shopshop.etc.BaseEntity;
import com.example.shopshop.likes.domain.Likes;
import com.example.shopshop.member.domain.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.log4j.Log4j2;

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
@Log4j2
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @NotNull
    private Integer saleRate;

//    private List<String> imageUrl = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member provider;


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


    public boolean stockCondition(String size, Integer count){

        if (size.equals("S")){ return this.sizeS - count >= 0;}
        if (size.equals("M")){ return this.sizeM - count >= 0;}
        if (size.equals("L")){ return this.sizeL - count >= 0;}
        return true;
    }

    public void removeStock(String size, Integer count){

        if (size.equals("S")){ this.sizeS -= count;}
        if (size.equals("M")){ this.sizeM -= count;}
        if (size.equals("L")){ this.sizeL -= count;}

    }

    public void addStock(String size, int count){

        log.info("on addStock size : " + size);
        if (size.equals("S")){ this.sizeS += count;}
        if (size.equals("M")){ this.sizeM += count;}
        if (size.equals("L")){
            log.info("L is True");
            this.sizeL += count;}

    }



}
