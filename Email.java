/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.models;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Meriem BEN AOUAYENE
 */
public class Email {
        public void sendMail (String adress,String subject,String message) throws MessagingException{
            
        String from ="meriem.benaouayene@esprit.tn";
        String pass="181JFT2243";

         String[] to ={adress};
        
        String host ="smtp.gmail.com";
        Properties prop =System.getProperties();
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.user", from);
        prop.put("mail.smtp.password", pass);
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        
        Session session =Session.getDefaultInstance(prop);
        
        
        
        MimeMessage msg =new MimeMessage(session);
       
        msg.setFrom(new InternetAddress(from));
        InternetAddress[] toadress=new InternetAddress[to.length];
        for (int i=0; i<to.length;i++){
            toadress[i]=new InternetAddress(to[i]);
        }
        for (int i=0; i<to.length;i++){
            msg.setRecipient(javax.mail.Message.RecipientType.TO, toadress[i]);
        }
        System.out.println(msg);
        msg.setSubject(subject);
        msg.setContent(message,"text/html; charset=utf-8");
        Transport transport=session.getTransport("smtp");
        transport.connect(host,from,pass);
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
        
    }
}
