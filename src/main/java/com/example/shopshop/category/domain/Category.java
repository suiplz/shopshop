package com.example.shopshop.category.domain;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.etc.BaseEntity;
import groovy.lang.Lazy;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Season season;

    @Enumerated(EnumType.STRING)
    private Gender gender;

}
