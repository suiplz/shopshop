package com.example.shopshop.member.repository;

import com.example.shopshop.member.domain.MemberRoleRequest;
import com.example.shopshop.member.dto.MemberRoleRequestDTO;
import com.example.shopshop.page.dto.PageRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRoleRequestRepository extends JpaRepository<MemberRoleRequest, Long> {


    Optional<MemberRoleRequest> findByMemberId(Long memberId);

    @Query("SELECT mr.id, mr.role, m.id, m.email, mr.regDate FROM MemberRoleRequest mr " +
            "INNER JOIN Member m ON m.id = mr.member.id ")
    Page<Object[]> getRoleRequestList(Pageable pageable);
}
