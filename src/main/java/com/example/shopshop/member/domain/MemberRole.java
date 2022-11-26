package com.example.shopshop.member.domain;

public enum MemberRole {
    MEMBER("MEMBER"),
    ROLE_PROVIDER("ROLE_PROVIDER"),
    ROLE_MANAGER("ROLE_MANAGER"),
    ROLE_ADMIN("ROLE_ADMIN");

    String role;

    MemberRole(String role) {
        this.role = role;
    }

    public String value() {
        return role;
    }
}
