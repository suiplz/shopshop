package com.example.shopshop.auth;

import com.example.shopshop.member.domain.Member;
import com.example.shopshop.member.domain.MemberRole;
import com.example.shopshop.member.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.parameters.P;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class PrincipalDetailsTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void memberRoleSetTest(){

        Optional<Member> result = memberRepository.findByEmail("asd@asd");
        if (result.isPresent()) {
            Member member = result.get();

            Set<MemberRole> roleSet = member.getRoleSet();
            PrincipalDetails principalDetails = new PrincipalDetails(member);
            Collection<? extends GrantedAuthority> authorities = principalDetails.getAuthorities();
            for (MemberRole memberRole : roleSet) {
                log.info("memberRole = " + memberRole);
            }

            for (GrantedAuthority authority : authorities) {
                log.info(authority.getAuthority());
            }
        }
    }

}