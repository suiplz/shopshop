package com.example.shopshop.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MemberRole {
    MEMBER("ROLE_MEMBER"),
    PROVIDER("ROLE_MEMBER, ROLE_PROVIDER"),
    MANAGER("ROLE_MEMBER, ROLE_MANAGER"),
    ADMIN("ROLE_MEMBER, ROLE_MANAGER, ROLE_ADMIN");

    private String value;

//    MemberRole(String role) {
//        this.role = role;
//    }
//
//    public String value() {
//        return role;
//    }
}
