package com.yusufcandmrz.chronicle.notification.service.impl;

import com.yusufcandmrz.chronicle.notification.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendWelcomeEmail(String to, String username) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject("Welcome to Chronicle!");
        message.setText(
                "Hello " + username + ",\n\n" +
                        "Welcome to Chronicle!\n\n" +
                        "Your account has been successfully created.\n\n" +
                        "Enjoy Chronicle!"
        );

        mailSender.send(message);
    }
}
