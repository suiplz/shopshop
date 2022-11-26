package com.example.shopshop.member.domain;

import com.example.shopshop.etc.BaseEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();


    public void changePassword(String password) {
        this.password = password;
    }

    public void changeAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return this.role.value();
    }
    public MemberRole setMemberRole(MemberRole memberRole) {
        this.role = memberRole;
        return this.role;
    }
//    public void addMemberRole(MemberRole memberRole) {
//        roleSet.add(memberRole);
//    }
}
