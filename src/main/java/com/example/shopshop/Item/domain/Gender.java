package com.example.shopshop.Item.domain;

public enum Gender {

    // Male, Female, Unisex

    MALE("MALE"),
    FEMALE("FEMALE"),
    UNISEX("UNISEX");

    private String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
