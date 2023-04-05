package com.example.shopshop.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberRoleRequestDTO {

    private Long id;

    private String role;

    private Long memberId;

    private String email;

    private LocalDateTime regDate;
}
