/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.group.services;

import com.example.group.entities.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

/**
 *
 * @author Panos
 */
@Service
public class NotificationService {
    
    
    private JavaMailSender javaMailSender;
    
    @Autowired
    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    
    public void sendNotification(User user) throws MailException, MessagingException, FileNotFoundException {
        
        
                
        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true);
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        helper.setTo(user.getEmail());
        helper.setFrom("teamproject1991@gmail.com");
        helper.setSubject("Registration Confirmation Email Bet Masters");
        helper.setText("<html><body><p>Welcome to our website Bet Masters, thanks for registering.</p><img src='cid:betmasterslogo'></body></html>", true);   
        
        File file = ResourceUtils.getFile(this.getClass().getResource("/static/images/bet.jpg"));
        FileSystemResource result = new FileSystemResource(file);
        helper.addInline("betmasterslogo", result);


        javaMailSender.send(mail);
    }
}
