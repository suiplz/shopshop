package com.example.shopshop.security.config;

import com.example.shopshop.member.repository.MemberRepository;;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    private final MemberRepository memberRepository;

    private final CorsConfig corsConfig;



    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().loginPage("/login")
                .usernameParameter("email")
                .permitAll()
                .and()
//                .formLogin().disable()
                .httpBasic().disable()
                .authorizeRequests(authorize -> authorize.antMatchers("/item/register")
                        .access("hasRole('Provider') or hasRole('Manager') or hasRole('Admin')")
                        .anyRequest()
                        .permitAll())
                .build();



    }

}
