package com.example.shopshop.member.service;

import com.example.shopshop.member.repository.MemberRoleRequestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {


    @Autowired
    private MemberRoleRequestRepository memberRoleRequestRepository;

    @Test
    void deleteRequest(){
        memberRoleRequestRepository.deleteById(3L);
    }

}