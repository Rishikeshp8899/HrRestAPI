package com.mindgate.main.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.io.*;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSimpleMessage(String to,String subject,String text,byte[] attachment,String name) {
        MimeMessage message=javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message,true);
            helper.setFrom("rishikeshp88@gmail.com");
            helper.setTo(to);
            helper.setText(text);
            helper.setSubject(subject);
            if(attachment!=null)
            {
            	ByteArrayResource file=new ByteArrayResource(attachment);
            	helper.addAttachment(name+".pdf", file);
            }
            javaMailSender.send(message);
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        message.setFrom(form);
//        message.setTo(to);
//        message.setText(text);
        
    }
    
    
    public boolean verifyemail(String to,String subject,String content) 
    {
    	try
    	{
//    		MimeMessageHelper messageHelper=new 
    		SimpleMailMessage mailMessage=new SimpleMailMessage();
    		mailMessage.setTo(to);
    		mailMessage.setFrom("rishikeshp88@gmail.com");
    		mailMessage.setSubject(subject);
    		mailMessage.setText(content);
    		javaMailSender.send(mailMessage);
    		return true;
    	}
    	catch (Exception e) 
    	{
			System.out.println(e.getMessage());
		}
    	
    	return false;
	}
}
