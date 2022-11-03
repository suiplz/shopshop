package com.example.shopshop.member.service;

import com.example.shopshop.member.domain.Member;
import com.example.shopshop.member.dto.MemberDTO;
import com.example.shopshop.member.dto.SignupDTO;

public interface MemberService {

    Long register(SignupDTO signupDTO);

    Member get(Long id);

    void modify(MemberDTO memberDTO);

    void remove(Long id);


    default Member dtoToEntity(MemberDTO dto) {

        Member member = Member.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .name(dto.getName())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .build();

        return member;
    }

    default MemberDTO entityToDTO(Member member) {

        MemberDTO dto = MemberDTO.builder()
                .id(member.getId())
                .email(member.getEmail())
                .password(member.getPassword())
                .name(member.getName())
                .phone(member.getPhone())
                .address(member.getAddress())
                .build();

        return dto;
    }
}
