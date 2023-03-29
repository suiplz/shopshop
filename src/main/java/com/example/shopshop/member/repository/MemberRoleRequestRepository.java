package com.example.shopshop.member.repository;

import com.example.shopshop.member.domain.MemberRoleRequest;
import com.example.shopshop.member.dto.MemberRoleRequestDTO;
import com.example.shopshop.page.dto.PageRequestDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRoleRequestRepository extends JpaRepository<MemberRoleRequest, Long> {


}
