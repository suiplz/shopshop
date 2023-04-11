package com.example.shopshop.member.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private Long id;

    private String email;

    private String password;

    private String passwordCheck; // for modify

    private String name;

    private String phone;

    private String address1;

    private String address2;

    private String address3;

    private int point;

}
