package com.example.shopshop.security.auth;

import com.example.shopshop.member.domain.Member;
import com.example.shopshop.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        log.info("email : " + email);
//        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("check Your Email"));
//        log.info("Principal Load : " + member);
//
//        return new PrincipalDetails(member);
//
//    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Check your email"));

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(member.getRole()));

        MemberContext memberContext = new MemberContext(member, roles);
        return memberContext;
    }
}
