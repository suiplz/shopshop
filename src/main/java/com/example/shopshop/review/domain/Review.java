package com.example.shopshop.review.domain;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.etc.BaseEntity;
import com.example.shopshop.member.domain.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = {"item", "member"})
public class Review extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    private String title;

    private String text;

    private Integer grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    @JsonIgnore
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_Id")
    @JsonIgnore
    private Member member;


    public void changeTitle(String title){
        this.title = title;
    }

    public void changeText(String text) {
        this.text = text;
    }

    public void changeGrade(Integer rate) {
        this.grade = rate;
    }
}
