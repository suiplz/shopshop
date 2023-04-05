package com.example.shopshop.Item.domain;

public enum ClothType {

    //    TOP, PANTS, OUTER, SHOES;
    상의("TOP"),
    하의("PANTS"),
    아우터("OUTER"),
    신발("SHOES");

    private String value;

    ClothType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
