package com.example.shopshop.auth;

import com.example.shopshop.member.domain.Member;
import com.example.shopshop.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("email : " + email);
        Optional<Member> result = memberRepository.findByEmail(email);
        if(!result.isPresent()) {
            throw new UsernameNotFoundException("Check Email or Password");
        }


        Member member = result.get();
        log.info("Principal Load : " + member);

        return new PrincipalDetails(member);

    }
}
