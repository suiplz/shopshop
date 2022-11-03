package com.example.shopshop.config;

import com.example.shopshop.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private CorsConfig corsConfig;
//
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .formLogin().loginPage("/member/login")
//                .permitAll()
//                .and()
//                .httpBasic().disable()
//                .apply(new MyCustomDsl())
//                .and()
//                .authorizeRequests(authorize -> authorize.antMatchers("/item/register")
//                        .access("hasRole('Role_Provider')")
//                        .anyRequest()
//                        .permitAll())
//                .build();
//
//
//
//    }
//
//
//    private class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
//
//        @Override
//        public void configure(HttpSecurity http) throws Exception {
//            http.getSharedObject(AuthenticationManager.class);
//            http
//                    .addFilter(corsConfig.corsFilter());
//
//        }
//    }
}
