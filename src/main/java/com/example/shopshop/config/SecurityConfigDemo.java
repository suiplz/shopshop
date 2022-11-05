package com.example.shopshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
//public class SecurityConfigDemo {
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests((requests) -> requests
////                .antMatchers("/", "/home", "/css/**", "/script/**", "/scripts/**", "/item/list", "/member/signup").permitAll()
//                .antMatchers("/**").permitAll()
//                .anyRequest().authenticated())
//                .formLogin((form) -> form
//                        .loginPage("/member/login")
//                        .usernameParameter("email")
//                        .loginProcessingUrl("/login")
//                        .permitAll()
//                )
//                .csrf().disable()
//                .logout((logout) -> logout.permitAll());
//
//
//        return http.build();
//
//    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//    }

//}
