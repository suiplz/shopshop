package com.example.shopshop.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupDTO {

    private String email;

    private String password;

    private String passwordCheck;

    private String name;

    private String phone;

    private String address;

    public MemberDTO toEntity() {

        MemberDTO memberDTO = MemberDTO.builder()
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .phone(this.phone)
                .address(this.address)
                .build();
        return memberDTO;
    }


}
