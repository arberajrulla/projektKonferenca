package com.ikubinfo.konferenca.utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class MailUtil {

	private static Logger log = Logger.getLogger(MailUtil.class);
		 
	public static void sendResetMail(String toAddress, String kodi) {
		  
		  final String from = "arber97pg@gmail.com";
		  final String password = "Paqomastiku1!";
		 
		  Properties prop = new Properties();
		  prop.put("mail.smtp.host", "smtp.gmail.com");
		  prop.put("mail.smtp.port", "465");
		  prop.put("mail.smtp.auth", "true");
		  prop.put("mail.smtp.socketFactory.port", "465");
		  prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		 
		  Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			  protected PasswordAuthentication getPasswordAuthentication() {
				  	return new PasswordAuthentication(from, password);
			  }
		  });
		 
		  try {
		   Message message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(from));
		   message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
		   message.setSubject("Konferenca X 2020, Ndryshim Fjalekalimi");
		    
		   String msg = "Ju lutemi vendosni ne faqen perkatese te Konferences X 2020 kodin: " + kodi + " dhe me pas vazhdoni me procedurat e rivendosjes se fjalekalimit!";
		    
		   MimeBodyPart mimeBodyPart = new MimeBodyPart();
		   mimeBodyPart.setContent(msg, "text/html");
		     
		   Multipart multipart = new MimeMultipart();
		   multipart.addBodyPart(mimeBodyPart);
   
		   message.setContent(multipart);
		   Transport.send(message);
		 
		   log.info("E-mail sent successfully");
		   UtilMessages.addMessageSuccess(null, "E-mail me procedurat per te rikthyer fjalekalimin u dergua me sukses!");
		  } catch (Exception e) {
			  log.info("E-mail wasn't sent successfully", e);
			  UtilMessages.addMessageError("Error! ", "Ndodhi nje gabim, E-mail nuk u dergua!");
		  }		

	}
	
	public static String codeGenerate() {
		String kodi = RandomStringUtils.randomAlphanumeric(6);
		if(!kodi.isEmpty()) {
			log.info("kodi i gjeneruar+ " + kodi);
			return kodi;
		}else {
			return "";
		}
		
	}
	
	
	
	
	
	
	
}

	

