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
            member.setMemberRole(MemberRole.MEMBER);
//            member.addMemberRole(MemberRole.ROLE_MEMBER);
            memberRepository.save(member);
            return member.getId();
        }
        return null;
    }

    @Override
    public MemberDTO get(Long id) {
        Optional<Member> result = memberRepository.findById(id);
        Member member = result.get();
        MemberDTO memberDTO = entityToDTO(member);
        return memberDTO;
    }

    @Override
    public void modify(MemberDTO memberDTO) {
        if (memberDTO.getPassword().equals(memberDTO.getPasswordCheck())) {

            Optional<Member> result = memberRepository.findById(memberDTO.getId());
            Member member = result.get();
            String rawPassword = memberDTO.getPassword();
            String encPassword = passwordEncoder.encode(rawPassword);
            member.changePassword(encPassword);
            member.changeAddress(memberDTO.getAddress());
            member.changePhone(memberDTO.getPhone());

            memberRepository.save(member);
        }

    }

    @Override
    public void remove(Long id) {

        memberRepository.deleteById(id);
    }
}

