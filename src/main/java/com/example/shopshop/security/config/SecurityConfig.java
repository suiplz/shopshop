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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().loginPage("/member/login").loginProcessingUrl("/member/login")
                .usernameParameter("email")
                .permitAll()
                .and()
//                .formLogin().disable()
                .httpBasic().disable()
                .authorizeRequests(authorize -> authorize.antMatchers("/item/test")
                        .hasAnyRole("MEMBER")
                        .anyRequest()
                        .permitAll())
//                .authorizeRequests(authorize -> authorize.antMatchers("/item/register")
//                        .access("hasRole('ROLE_PROVIDER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
//                        .antMatchers("/item/list")
//                        .access("hasRole('ROLE_MEMBER')")
//                        .anyRequest()
//                        .permitAll())
                .build();



    }

}
