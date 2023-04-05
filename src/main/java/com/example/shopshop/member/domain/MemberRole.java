package com.example.shopshop.member.domain;

import lombok.Getter;

@Getter
public enum MemberRole {
//    회원("ROLE_MEMBER"),
//    판매자("ROLE_MEMBER, ROLE_PROVIDER"),
//    매니저("ROLE_MEMBER, ROLE_MANAGER"),
//    관리자("ROLE_MEMBER, ROLE_MANAGER, ROLE_ADMIN");

    MEMBER("ROLE_MEMBER"),
    PROVIDER("ROLE_PROVIDER"),
    MANAGER("ROLE_MANAGER"),
    ADMIN("ROLE_ADMIN");

    private String value;

    MemberRole(String value) {
        this.value = value;
    }

    public static MemberRole fromValue(String value) {
        for (MemberRole role : MemberRole.values()) {
            if (role.getValue().equals(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("찾을 수 없습니다.");
    }

    public String getValue() {
        return value;
    }

//    MemberRole(String role) {
//        this.role = role;
//    }
//
//    public String value() {
//        return role;
//    }
}
