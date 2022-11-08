package com.example.shopshop.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Setter
@ToString
public class MemberAuthDTO {

    private String email;

    private String name;
}
