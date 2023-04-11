package com.example.shopshop.security.config;

import com.example.shopshop.security.handler.CustomAuthenticationFailureHandler;
import com.example.shopshop.security.handler.CustomAuthenticationSuccessHandler;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    private AuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> {
            web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
            web.ignoring().antMatchers("/favicon.ico", "/resources/**", "/error");
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//        http
//            .csrf().disable()
//            .httpBasic().disable()
//            .authorizeRequests(authorize -> authorize.antMatchers("/item/test")
//                    .hasAnyRole("ROLE_MEMBER")
//                    .anyRequest()
//                    .permitAll());

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/item/test").hasRole("MEMBER")
//                .antMatchers("/orders/**", "/cart/**", "/review/**").hasAnyRole("MEMBER", "PROVIDER", "MANAGER", "ADMIN")
//                .antMatchers("/member/memberRoleManage").hasRole("ADMIN")
                .antMatchers("/item/register").hasAnyRole("PROVIDER", "MANAGER", "ADMIN")
                .anyRequest()
                .permitAll();

        http
                .formLogin().loginPage("/member/login")
                .loginProcessingUrl("/member/login")
                .defaultSuccessUrl("/")
                .successHandler(customAuthenticationSuccessHandler())
//                .failureHandler(customAuthenticationFailureHandler())
                .usernameParameter("email")
                .and()
                .logout().logoutUrl("/logout")
                .and().exceptionHandling()
                .accessDeniedPage("/error-page/403.html") // 403 에러 페이지
        ;


        return http.build();


    }

}
