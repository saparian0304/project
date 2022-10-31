package util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	
	// 매개변수가 될 수 있는 것들 : 발신자, 수신자, 제목, 내용
	public static void sendMail(
			String from, String to, String subject, String content) {
		
		// 1. 메일 서버 정보 설정 (Property 객체로 처리 - Map인터페이스의 하위 클래스)
		Properties prop = System.getProperties();
		prop.put("mail.smtp.host", "smtp.naver.com");	// SMTP 서버명
		prop.put("mail.smtp.port", "465");				// 포트번호
		prop.put("mail.smtp.auth", "true");			// 인증
		prop.put("mail.smtp.ssl.enable", "true");	// 보안연결 필요
		prop.put("mail.smtp.ssl.protocols", "TLSv1.2");	// No appropriate protocol 에러때문
		
		// 2. 인증을 위해 Session 객체 생성 ( 매개변수 : Properties객체, Authenticator 객체)
		// Authenticator 추상클래스의 익명객체 생성 후 
		// getPasswordAuthentication 매서드 재정의
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("id", "password!");	// 발신자 아이디, 비밀번호 정보
			    }
		});
		session.setDebug(true);
		
		// 3. MimeMessage 객체 생성(발신자, 수신자, 제목, 내용 설정)
		try {
			MimeMessage mm = new MimeMessage(session);
			mm.setFrom(new InternetAddress(from));	// 발신자
			mm.setRecipient(Message.RecipientType.TO, new InternetAddress(to));	// 수신자
			mm.setSubject(subject);		// 제목
			//mm.setText(content);	// 내용  setContent는 html 형태로 메일을 보내고 싶을때 사용
			mm.setContent(content, "text/html; charset=utf-8");
			
			// 4. 메일 발송
			Transport.send(mm);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	// 메일이 잘 전송되는지 확인하기 위해 만든 메서드
	public static void main(String[] args) {
		sendMail("toi90@naver.com", "anjp0304@gmail.com", "test", "<b>test content 한글</b>");
	}
}
