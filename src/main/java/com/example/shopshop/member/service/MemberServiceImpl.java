package com.example.shopshop.member.service;

import com.example.shopshop.member.domain.Member;
import com.example.shopshop.member.dto.MemberDTO;
import com.example.shopshop.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Long register(MemberDTO memberDTO) {
        Member member = dtoToEntity(memberDTO);
        memberRepository.save(member);
        return member.getId();
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
