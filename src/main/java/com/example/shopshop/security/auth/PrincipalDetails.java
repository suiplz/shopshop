package com.example.shopshop.security.auth;

import com.example.shopshop.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrincipalDetails implements UserDetails {

    private Member member;

    private String role;

    public PrincipalDetails(Member member) {
        super();
        this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return member.getRoleSet().stream().map(r -> new SimpleGrantedAuthority(r.name()))
//                .collect(Collectors.toSet());

        Collection<GrantedAuthority> authorities = new ArrayList<>();

        for (String role : role.split(",")) {
            authorities.add(new SimpleGrantedAuthority(role));

        }
        return authorities;

    }

    public Member getMember() {
        return member;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    public String getEmail() {
        return member.getEmail();
    }

    @Override
    public String getUsername() {
        return member.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean isAuthenticated(Long id) {
        return id == this.member.getId();
    }
}
