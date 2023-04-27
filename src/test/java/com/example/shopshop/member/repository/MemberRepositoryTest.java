package com.example.shopshop.member.repository;

import com.example.shopshop.member.domain.Member;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.stream.IntStream;

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
                    .email("member" + i + "@xmail.com")
                    .password("1231")
                    .name("name" + i)
                    .build();
            memberRepository.save(member);
        });
    }

    @Test
    void findMembers() {

        Optional<Member> result = memberRepository.findById(1L);
        if (result.isPresent()) {
            Member member = result.get();
            log.info("member = " + member);
        }
    }

    @Test
    void findMemberByEmailTest() {

        Member member = memberRepository.findByEmail("fwe@fea").orElseThrow(() -> new UsernameNotFoundException("Check Your Email"));

        System.out.println("member = " + member);

    }

    @Test
    void testForOrder() {

        Optional<Member> result = memberRepository.findById(3L);
        Member member = result.get();

        member.addPoint(100000);
        memberRepository.save(member);
    }


    @Test
    void memberRemove(){

        Member member = memberRepository.findByEmail("spade231@naver.com").orElseThrow(() -> new IllegalArgumentException());
        memberRepository.deleteById(member.getId());
    }

    @Test
    void forAuthTest() {
        Member member = memberRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException());
        member.setMemberRole("ROLE_ADMIN");
        memberRepository.save(member);
    }
}