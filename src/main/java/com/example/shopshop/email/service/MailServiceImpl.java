package com.example.shopshop.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;


    public void sendEmail(String to, String subject, String body) throws MessagingException {
        final MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception{
                final MimeMessageHelper mailHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

                mailHelper.setFrom("shopshop.com");
                mailHelper.setTo(to);
                mailHelper.setSubject(subject);
                mailHelper.setText(body);

            }

        };

        try {
            javaMailSender.send(preparator);

        } catch(Exception e) {
            e.printStackTrace();
        }
//        MimeMessage message = javaMailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        helper.setFrom("shopshop.com");
//        helper.setTo(to);
//        helper.setSubject(subject);
//        helper.setText(body, true);
//        try {
//            javaMailSender.send(message);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
