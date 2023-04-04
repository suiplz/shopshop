package com.example.shopshop.member.service;

import com.example.shopshop.member.domain.Member;
import com.example.shopshop.member.repository.MemberRepository;
import com.example.shopshop.member.repository.MemberRoleRequestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {


    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;


    @Autowired
    private MemberRoleRequestRepository memberRoleRequestRepository;

    @Test
    void deleteRequest(){
        memberRoleRequestRepository.deleteById(3L);
    }

    @Test
    void changeMember() {
        Optional<Member> result = memberRepository.findById(1L);
        Member member = result.get();

    }
}