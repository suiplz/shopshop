package com.example.shopshop.member.domain;

import com.example.shopshop.etc.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    private String phone;

    private String address;


    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;


    public void changePassword(String password) {
        this.password = password;
    }

    public void changeAddress(String address) {
        this.address = address;
    }
}
