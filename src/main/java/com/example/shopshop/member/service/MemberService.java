package com.example.shopshop.member.service;

import com.example.shopshop.Item.dto.ItemDTO;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.member.domain.MemberRole;
import com.example.shopshop.member.domain.MemberRoleRequest;
import com.example.shopshop.member.dto.MemberDTO;
import com.example.shopshop.member.dto.MemberRoleRequestDTO;
import com.example.shopshop.member.dto.SignupDTO;
import com.example.shopshop.page.dto.PageRequestDTO;
import com.example.shopshop.page.dto.PageResultDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface MemberService {

    Long register(SignupDTO signupDTO);

    MemberDTO get(Long id);

    void modify(MemberDTO memberDTO);

    void remove(Long id);

    void requestRole(Long id, String role);

    void applyMemberRole(Long id, String memberRole);

    PageResultDTO<MemberRoleRequestDTO, Object[]> getRequestMemberRoleList(PageRequestDTO requestDTO);


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

    default MemberRoleRequestDTO entityRequestToDTO(Long id, String role, Long memberId, String email, LocalDateTime regDate){

        MemberRoleRequestDTO memberRoleRequestDTO = MemberRoleRequestDTO
                .builder()
                .id(id)
                .role(role)
                .memberId(memberId)
                .email(email)
                .regDate(regDate)
                .build();

        return memberRoleRequestDTO;
    }
}
