package com.example.shopshop.Item.domain;
public enum Season {

//    SPRING, SUMMER, AUTUMN, WINTER
    SPRING("SPRING"),
    SUMMER("SUMMER"),
    AUTUMN("AUTUMN"),
    WINTER("WINTER");

    private String value;

    Season(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
