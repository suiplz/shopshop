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

    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    @NotBlank
    private String address;

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

    public void changeAddress(String address) {
        this.address = address;
    }

    public void addPoint(int point) {
        this.point += point;
    }

    public void reducePoint(int point) {
        this.point -= point;
    }

    //    @Enumerated(EnumType.STRING)
//    private MemberRole role;
//
//    public MemberRole setMemberRole(MemberRole memberRole) {
//        this.role = memberRole;
//        return this.role;
//    }
    public String setMemberRole(String memberRole) {
        this.role = memberRole;
        return this.role;
    }

}
