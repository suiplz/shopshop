package com.example.shopshop.member.repository;

import com.example.shopshop.member.domain.Member;
import com.example.shopshop.member.domain.MemberRole;
import com.example.shopshop.member.dto.SignupDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void insertMembers() {

        IntStream.rangeClosed(0, 100).forEach(i -> {
            Member member = Member.builder()
                    .name("member" + i)
                    .email("member" +i + "@xmail.com")
                    .password("1231")
                    .name("name" + i)
                    .build();
            memberRepository.save(member);
        });
    }

    @Test
    void findMembers(){

        Optional<Member> result = memberRepository.findById(1L);
        if (result.isPresent()){
            Member member= result.get();
            log.info("member = " + member);
        }
    }

    @Test
    void roleSetTest() {

        Member member = Member.builder()
                .name("asd")
                .email("asd@asd")
                .password("123")
                .memberRole(MemberRole.MEMBER)
                .address("gqwe")
                .phone("125")
                .build();
        memberRepository.save(member);

        Optional<Member> result = memberRepository.findById(1L);
        Member member1 = result.get();

        member1.addMemberRole(MemberRole.MEMBER);

        Set<MemberRole> roleSet = member1.getRoleSet();
        for (MemberRole memberRole : roleSet) {
            log.info("memberRole" + memberRole);
        }

    }

}