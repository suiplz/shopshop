package com.example.shopshop.etc.controller;

import com.example.shopshop.member.domain.Member;
import com.example.shopshop.security.auth.PrincipalDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class SampleRestController {

    @PostMapping("/token")
    public String token() {

        return "<h1>token</h1>";
    }

    @GetMapping("/principal")
    public Member principal(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return principalDetails.getMember();
    }
}
