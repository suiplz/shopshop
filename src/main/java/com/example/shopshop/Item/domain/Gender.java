package com.example.shopshop.Item.domain;

public enum Gender {

    // Male, Female, Unisex

    남성("MALE"),
    여성("FEMALE"),
    혼용("UNISEX");

    private String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
