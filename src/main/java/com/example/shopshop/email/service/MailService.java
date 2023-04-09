package com.example.shopshop.email.service;

import javax.mail.MessagingException;

public interface MailService {

    void sendEmail(String to, String subject, String body) throws MessagingException;
}
