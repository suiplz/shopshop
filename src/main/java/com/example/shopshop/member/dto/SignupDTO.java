package com.example.shopshop.member.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupDTO {

    private String email;

    private String password;

    private String name;

    private String phone;

    private String address;


}
