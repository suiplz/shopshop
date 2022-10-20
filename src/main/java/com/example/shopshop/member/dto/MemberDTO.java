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

    private String name;

    private String address;

}
