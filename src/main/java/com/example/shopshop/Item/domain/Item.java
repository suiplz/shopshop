package com.example.shopshop.Item.domain;

import com.example.shopshop.category.domain.Category;
import com.example.shopshop.etc.BaseEntity;
import com.example.shopshop.member.domain.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Log4j2
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String itemName;

    private Integer price;

    private Integer sizeS;

    private Integer sizeM;

    private Integer sizeL;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    private Integer saleRate;

    private Integer salePrice;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member provider;

    public void changeItem(String itemName, Integer price, Integer sizeS, Integer sizeM, Integer sizeL, Integer saleRate, Integer salePrice) {
        this.itemName = itemName;
        this.price = price;
        this.sizeS = sizeS;
        this.sizeM = sizeM;
        this.sizeL = sizeL;
        this.saleRate = saleRate;
        this.salePrice = salePrice;
    }


    public boolean stockCondition(String size, Integer count) {

        if (size.equals("S")) {
            return this.sizeS - count >= 0;
        }
        if (size.equals("M")) {
            return this.sizeM - count >= 0;
        }
        if (size.equals("L")) {
            return this.sizeL - count >= 0;
        }
        return true;
    }

    public void removeStock(String size, Integer count) {

        if (size.equals("S")) {
            this.sizeS -= count;
        }
        if (size.equals("M")) {
            this.sizeM -= count;
        }
        if (size.equals("L")) {
            this.sizeL -= count;
        }

    }

    public void addStock(String size, int count) {

        log.info("on addStock size : " + size);
        if (size.equals("S")) {
            this.sizeS += count;
        }
        if (size.equals("M")) {
            this.sizeM += count;
        }
        if (size.equals("L")) {
            log.info("L is True");
            this.sizeL += count;
        }

    }


}
