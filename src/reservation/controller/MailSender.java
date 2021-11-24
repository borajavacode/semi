package reservation.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import reservation.vo.Reservation;



public class MailSender {
	
	public int mailSend2(String email, Reservation rv) {
		boolean result = false;
		String s = rv.getRvName()+"님 예약하신 시간은 "+rv.getRvDate()+" "+rv.getRvTime()+" 이며 "+rv.getRvPoint()+"지점 인원은 "+rv.getRvPerson()+"명 입니다. 다른문의사항이 있으시면 문의게시판에 남겨주세요.";
		
		
		Properties prop = System.getProperties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		Session session = Session.getDefaultInstance(prop,new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				PasswordAuthentication pa = new PasswordAuthentication("tyooirsu1@gmail.com", "rnrel1324");
				return pa;
			}
		});
		MimeMessage msg = new MimeMessage(session);
		
		try {
			msg.setSentDate(new Date());
			//보내는사람
			msg.setFrom(new InternetAddress("tyooirsu1@gmail.com","KH escape"));
			
			//받는사람
			InternetAddress to = new InternetAddress(email);
			msg.setRecipient(Message.RecipientType.TO,to);
			//제목
			msg.setSubject("KH escape 예약안내","UTF-8");
			msg.setContent(s,"text/html;charset=UTF-8");
			Transport.send(msg);
			result=true;
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(result) {
			return 1;
		}else {
			return 0;
		}
		
		
	}
}
