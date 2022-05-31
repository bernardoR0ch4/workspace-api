package com.residencia.comercio.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Value("${spring.mail.host}")
	private String mailHost;

	@Value("${spring.mail.host}")
	private String mailPort;

	@Value("${spring.mail.host}")
	private String mailUsername;

	@Value("${spring.mail.host}")
	private String mailPassword;

	@Autowired
	JavaMailSender mailSender;

	public EmailService(JavaMailSender JavaMailSender) {
		this.mailSender = JavaMailSender;
	}
	
	public void enviarEmailTexto(String destinatarioEmail, String assunto, String mensagemEmail) {
		SimpleMailMessage sMailMessage = new SimpleMailMessage();
		
		sMailMessage.setTo(destinatarioEmail);
		sMailMessage.setSubject(assunto);
		sMailMessage.setText(mensagemEmail);
		
		sMailMessage.setFrom("teste@teste.com");
		
		mailSender.send(sMailMessage);
	}
	
	public void enviarEmailHTML(String destinatarioEmail, String assunto, String mensagemEmail) throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		String htmlMsg = "<h3>Hello World!</h3>";
		helper.setText(htmlMsg, true);
		helper.setTo(destinatarioEmail);
		helper.setSubject(assunto);
		helper.setFrom("teste@teste.com");
		mailSender.send(mimeMessage);
	}
	

}
