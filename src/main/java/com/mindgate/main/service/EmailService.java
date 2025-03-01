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
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender javaMailSender;

	public int sendSimpleMessage(String to,String subject,String text,String form,byte[] attachment,String candidateId) {
		MimeMessage message=javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message,true);
			helper.setFrom(form);
			helper.setTo(to);
			helper.setText(text);
			helper.setSubject(subject);
			ByteArrayResource file=new ByteArrayResource(attachment);
			helper.addAttachment(candidateId+".pdf", file);
			javaMailSender.send(message);
			return 1;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	

	public int sendSimpleMessage(String to,String subject,String text,String form) {
		MimeMessage message=javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message,true);
			helper.setFrom(form);
			helper.setTo(to);
			helper.setText(text);
			helper.setSubject(subject);
			javaMailSender.send(message);
			return 1;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
}
