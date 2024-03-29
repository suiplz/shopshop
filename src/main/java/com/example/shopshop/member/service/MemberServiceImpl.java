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
import java.util.Random;
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

//            Random random = new Random();
//            StringBuilder sb = new StringBuilder();
//
//            for (int i = 0; i < 5; i++) {
//                sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
//            }
//            String randomString = sb.toString();


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
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        MemberDTO memberDTO = entityToDTO(member);
        return memberDTO;
    }

    @Override
    public void modify(MemberDTO memberDTO) {
        if (memberDTO.getPassword().equals(memberDTO.getPasswordCheck())) {

            Member member = memberRepository.findById(memberDTO.getId()).orElseThrow(() -> new IllegalArgumentException());
            String rawPassword = memberDTO.getPassword();
            String encPassword = passwordEncoder.encode(rawPassword);
            member.changePassword(encPassword);
            member.changeAddress1(memberDTO.getAddress1());
            member.changeAddress2(memberDTO.getAddress2());
            member.changeAddress3(memberDTO.getAddress3());
            member.changePhone(memberDTO.getPhone());

            memberRepository.save(member);
        }

    }

    @Override
    public void requestRole(Long id, String role) {

        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
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
        MemberRoleRequest memberRoleRequest = memberRoleRequestRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        Long memberId = memberRoleRequest.getMember().getId();
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException());

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

    @Override
    public void makeNewPassword(String email, String newPassword) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException());
        String encPassword = passwordEncoder.encode(newPassword);
        member.changePassword(encPassword);
        memberRepository.save(member);

    }
}

