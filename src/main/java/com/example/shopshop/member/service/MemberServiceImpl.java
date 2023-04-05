package com.example.shopshop.member.service;

import com.example.shopshop.member.domain.Member;
import com.example.shopshop.member.domain.MemberRole;
import com.example.shopshop.member.domain.MemberRoleRequest;
import com.example.shopshop.member.dto.MemberDTO;
import com.example.shopshop.member.dto.MemberRoleRequestDTO;
import com.example.shopshop.member.dto.SignupDTO;
import com.example.shopshop.member.repository.MemberRepository;
import com.example.shopshop.member.repository.MemberRoleRequestRepository;
import com.example.shopshop.page.dto.PageRequestDTO;
import com.example.shopshop.page.dto.PageResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberRoleRequestRepository memberRoleRequestRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Long register(SignupDTO signupDTO) {
        if (signupDTO.getPassword().equals(signupDTO.getPasswordCheck())) {

            String rawPassword = signupDTO.getPassword();
            String encPassword = passwordEncoder.encode(rawPassword);
            signupDTO.setPassword(encPassword);

            MemberDTO memberDTO = signupDTO.toEntity();
            Member member = dtoToEntity(memberDTO);
            member.setMemberRole(MemberRole.MEMBER.getValue());
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
    public void requestRole(Long id, String role) {

        Optional<Member> result = memberRepository.findById(id);
        Member member = result.get();
        MemberRole memberRole = MemberRole.fromValue(role);
        MemberRoleRequest memberRoleRequest = MemberRoleRequest.builder().member(member).role(memberRole.getValue()).build();
        Optional<MemberRoleRequest> requestResult = memberRoleRequestRepository.findByMemberId(id);
        if (requestResult.isPresent()) {
            memberRoleRequest = requestResult.get();
            memberRoleRequest.setRole(memberRole.getValue());
        }
        memberRoleRequestRepository.save(memberRoleRequest);


    }

    @Override
    public void applyMemberRole(Long id, String role) {
        Optional<MemberRoleRequest> result = memberRoleRequestRepository.findById(id);
        MemberRoleRequest memberRoleRequest = result.get();
        Long memberId = memberRoleRequest.getMember().getId();
        Optional<Member> memberResult = memberRepository.findById(memberId);
        Member member = memberResult.get();
        member.setMemberRole(role);
        memberRepository.save(member);
        memberRoleRequestRepository.deleteById(id);
    }

    @Override
    public PageResultDTO<MemberRoleRequestDTO, Object[]> getRequestMemberRoleList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable();

        Page<Object[]> result = memberRoleRequestRepository.getRoleRequestList(pageable);

        Function<Object[], MemberRoleRequestDTO> fn = (arr -> {
            return entityRequestToDTO(
                    (Long) arr[0],
                    (String) arr[1],
                    (Long) arr[2],
                    (String) arr[3],
                    (LocalDateTime) arr[4]);
        });
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public void remove(Long id) {

        memberRepository.deleteById(id);
    }
}

