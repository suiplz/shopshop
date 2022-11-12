package com.example.shopshop.member.service;

import com.example.shopshop.member.domain.Member;
import com.example.shopshop.member.domain.MemberRole;
import com.example.shopshop.member.dto.MemberDTO;
import com.example.shopshop.member.dto.SignupDTO;
import com.example.shopshop.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Long register(SignupDTO signupDTO) {
        if (signupDTO.getPassword().equals(signupDTO.getPasswordCheck())) {

            String rawPassword = signupDTO.getPassword();
            String encPassword = passwordEncoder.encode(rawPassword);
            signupDTO.setPassword(encPassword);

            MemberDTO memberDTO = signupDTO.toEntity();
            Member member = dtoToEntity(memberDTO);
            member.setMemberRole(MemberRole.ROLE_MEMBER);
//            member.addMemberRole(MemberRole.ROLE_MEMBER);
            memberRepository.save(member);
            return member.getId();
        }
        return null;
    }

    @Override
    public Member get(Long id) {
        Optional<Member> result = memberRepository.findById(id);
        if(result.isPresent()){
            Member member = result.get();
            return member;
        }
        return null;
    }

    @Override
    public void modify(MemberDTO memberDTO) {
        Optional<Member> result = memberRepository.findById(memberDTO.getId());
        Member member = result.get();
        member.changePassword(memberDTO.getPassword());
        member.changeAddress(memberDTO.getAddress());

        memberRepository.save(member);

    }

    @Override
    public void remove(Long id) {

        memberRepository.deleteById(id);
    }
}

