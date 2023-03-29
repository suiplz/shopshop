package com.example.shopshop.member.dto;

import com.example.shopshop.member.domain.Member;
import com.example.shopshop.member.domain.MemberRole;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class MemberRoleRequestDTO {

    private Long id;

    private Member member;

    private MemberRole role;
}
