/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketpalce.serverside.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author Idris Siddiq
 */
@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    
    public void sendEmail(String toEmail, String username, String password){
        String url = "http://localhost:8082/api/register/setPassword/" + username;
        String body = "Your account is ready to use and please do not show it to anyone else! \n"
                + "This is your username and password"
                + " : "
                + "\nUsername : " + username
                + "\nPassword : " + password;
        String subject = "Your account is registered!";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("muhammadidrissiddiq@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        
        mailSender.send(message);
        
        System.out.println("Berhasil kirim email..");
    }
    
    public void forgotEmail(String toEmail, String username, String password){
        String url = "http://localhost:8082/api/register/setPassword/" + username;
        String body = "This is ypur new account! \n"
                + "Be kindly to remember your username and password"
                + "\nUsername : " + username
                + "\nPassword : " + password;
        String subject = "Your account is registered!";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("muhammadidrissiddiq@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        
        mailSender.send(message);
        
        System.out.println("Berhasil kirim email..");
    }
}
