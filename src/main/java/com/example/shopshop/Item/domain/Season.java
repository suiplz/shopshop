package com.example.shopshop.Item.domain;
public enum Season {

//    SPRING, SUMMER, AUTUMN, WINTER
    봄("SPRING"),
    여름("SUMMER"),
    가을("AUTUMN"),
    겨울("WINTER");

    private String value;

    Season(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
