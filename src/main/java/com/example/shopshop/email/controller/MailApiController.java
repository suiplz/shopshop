package com.example.shopshop.email.controller;

import com.example.shopshop.email.service.MailService;
import com.example.shopshop.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.Random;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class MailApiController {

    private final MailService mailService;

    @PostMapping("/emailCheck")
    public ResponseEntity<String> emailCheck(@RequestBody String email) throws MessagingException {

        Random random = new Random();
        Integer emailCheckNumber  = random.nextInt(888888) + 111111;
        String body = "인증번호는 " +  emailCheckNumber + " 입니다.";
        mailService.sendEmail(email, "shopshop 인증번호", body);
        return ResponseEntity.ok().body(emailCheckNumber.toString());
    }

    @PostMapping("/newPassword")
    public ResponseEntity<String> newPassword(@RequestBody String email) throws MessagingException {

        Random random = new Random();
        Integer newPassword  = random.nextInt(88888888) + 11111111;
        String body = "새 비밀번호는 " +  newPassword + " 입니다. 로그인 후 바로 변경해주세요";
        mailService.sendEmail(email, "shopshop 새 비밀번호", body);
        return ResponseEntity.ok().body(newPassword.toString());
    }
}
