package com.example.shopshop.member.domain;

import com.example.shopshop.etc.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String name;

    private String phone;

    private String address1;

    private String address2;

    private String address3;

    private String role;

    private int point;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();


    public void changePassword(String password) {
        this.password = password;
    }

    public void changePhone(String phone) {
        this.phone = phone;
    }

    public void changeAddress1(String address1) {
        this.address1 = address1;
    }

    public void changeAddress2(String address2) {
        this.address2 = address2;
    }

    public void changeAddress3(String address3) {
        this.address3 = address3;
    }

    public void addPoint(int point) {
        this.point += point;
    }

    public void reducePoint(int point) {
        this.point -= point;
    }


    public String setMemberRole(String memberRole) {
        this.role = memberRole;
        return this.role;
    }

}
