package com.payhub.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailService{

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendSimpleEmail(String to, String subject, String content){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        javaMailSender.send(message);

    }

    public void sendMailWithAttachment(String to, String subject, String content, String pathToAttachment) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(fromEmail);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content);

        FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
        helper.addAttachment("Attachment", file);

        javaMailSender.send(message);

    }

}
