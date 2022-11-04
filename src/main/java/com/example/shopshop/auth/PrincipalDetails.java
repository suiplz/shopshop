package com.example.shopshop.auth;

import com.example.shopshop.member.domain.Member;
import com.example.shopshop.member.domain.MemberRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

//public class PrincipalDetails implements UserDetails {
//
//    private Member member;
//
//    public PrincipalDetails(Member member) {
//        this.member = member;
//    }
//
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//
//    }
//
//    @Override
//    public String getPassword() {
//        return member.getPassword();
//    }
//
//    public String getEmail() {
//        return member.getEmail();
//    }
//
//    @Override
//    public String getUsername() {
//        return member.getName();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
