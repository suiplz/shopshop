package com.example.shopshop.etc.mailtest;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import java.util.Random;

@Controller
@Log4j2
@RequestMapping("/email")
public class MainTestController {


    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/sendEmail")
    public void sendEmail() {

    }
    @PostMapping(value = "/sendEmail")
    public void sendEmail(@RequestBody EmailFormDTO emailFormDTO) throws Exception{

        String from = "shopshop.com";
        log.info("9898 result : " + emailFormDTO.getBody());
        Random random = new Random();
        int emailCheckNumber  = random.nextInt(888888) + 111111;
        String body = "인증번호는 " +  emailCheckNumber + " 입니다.";

        final MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception{
                final MimeMessageHelper mailHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

                mailHelper.setFrom(from);
                mailHelper.setTo(emailFormDTO.getTo());
                mailHelper.setSubject(emailFormDTO.getSubject());
                mailHelper.setText(body);

            }

        };

        try {
            mailSender.send(preparator);

        } catch(Exception e) {
            e.printStackTrace();
        }

    }




}
