package com.example.shopshop.member.dto;

import com.example.shopshop.member.domain.Member;
import com.example.shopshop.member.domain.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
