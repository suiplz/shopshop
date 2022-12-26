package com.example.shopshop.aop.aspect;

import com.example.shopshop.aop.annotation.LoginCheck;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.security.auth.PrincipalDetails;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Log4j2
public class LoginCheckAspect {


//    @Around("@annotation(com.example.shopshop.aop.annotation.LoginCheck) && @annotation(loginCheck) && @annotation(authenticationPrincipal)")
//    public Object adminLoginCheck(ProceedingJoinPoint proceedingJoinPoint, LoginCheck loginCheck, AuthenticationPrincipal authenticationPrincipal) throws Throwable{
//
//
//    }
//

//    @Around("@annotation(loginCheck)")
//    public Object adminLoginCheck(ProceedingJoinPoint proceedingJoinPoint, LoginCheck loginCheck) throws Throwable{
//
//        PrincipalDetails principalDetails = new PrincipalDetails();
//        Member member = principalDetails.getMember();
//        if (principalDetails.isAuthenticated(member.getId())) {
//
//            return proceedingJoinPoint.proceed();
//        }
//        else {
//            throw new Exception("권한이 없습니다.");
//        }
//
//    }

}
