package com.example.shopshop.Item.domain;

import org.springframework.stereotype.Component;

public enum ClothType {

//    TOP, PANTS, OUTER, SHOES;
    TOP("TOP"),
    PANTS("PANTS"),
    OUTER("OUTER"),
    SHOES("SHOES");

    private String value;

    ClothType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
