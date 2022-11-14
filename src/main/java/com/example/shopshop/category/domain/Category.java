//package com.example.shopshop.category.domain;
//
//import com.example.shopshop.etc.BaseEntity;
//import com.example.shopshop.Item.domain.ClothType;
//import com.example.shopshop.Item.domain.Gender;
//import com.example.shopshop.Item.domain.Season;
//import lombok.*;
//
//import javax.persistence.*;
//
//@Entity
//@Builder
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//public class Category extends BaseEntity {
//
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Enumerated(EnumType.STRING)
//    private ClothType clothType;
//
//    @Enumerated(EnumType.STRING)
//    private Season season;
//
//    @Enumerated(EnumType.STRING)
//    private Gender gender;
//
//}
