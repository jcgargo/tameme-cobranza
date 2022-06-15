package com.tameme.cobranza.common.service;

import java.io.File;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.tameme.cobranza.common.entity.EmailConfig;
import com.tameme.cobranza.common.service.interfaz.ICorreoService;
import com.tameme.cobranza.common.service.interfaz.IEmailConfigService;

@Service
public class CorreoService implements ICorreoService {
	
	@Autowired
	private IEmailConfigService configService;
	
	@Autowired
	private JavaMailSender emailSender;

	@Override
	public void enviar(String destinatario, List<String> archivos) throws MessagingException {
	    
		MimeMessage message = emailSender.createMimeMessage();
	     
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);
	    
	    helper.setFrom("cobranza@tameme.com");
	    helper.setTo(destinatario);
	    helper.setSubject("Orden de pago");
	    helper.setText("Le enviamos la orden de pago");
	    
	    for(String s: archivos) {
	    	File f = new File(s);
		    FileSystemResource file = new FileSystemResource(f);
		    helper.addAttachment(f.getName(), file);	    	
	    }

	    emailSender.send(message);
	}

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		EmailConfig config = configService.busca();
		
		mailSender.setHost(config.getHost());
		mailSender.setPort(config.getPort());
		mailSender.setUsername(config.getUsername());
		mailSender.setPassword(config.getPassword());

	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "false");
	    props.put("mail.debug", "true");
	    
		return mailSender;
	}
}
