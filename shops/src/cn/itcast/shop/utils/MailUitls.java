package cn.itcast.shop.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailUitls {
	/**
	 * �����ʼ��ķ���
	 * @param to	:�ռ���
	 * @param code	:������
	 */
	public static void sendMail(String to,String code){
		/**
		 * 1.���һ��Session����.
		 * 2.����һ�������ʼ��Ķ���Message.
		 * 3.�����ʼ�Transport
		 */
		// 1.������Ӷ���
		Properties props = new Properties();
		props.setProperty("mail.host", "smtp.163.com");
		props.setProperty("mail.smtp.auth", "true");
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("just_do_it_first@163.com", "xin260824");
			}
			
		});
		// 2.�����ʼ�����:
		Message message = new MimeMessage(session);
		// ���÷�����:
		try {
			message.setFrom(new InternetAddress("just_do_it_first@163.com"));
			// �����ռ���:
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			// ���� CC   ����BCC
			// ���ñ���
			message.setSubject("���Թ������ùٷ������ʼ�");
			// �����ʼ�����:
			message.setContent("<h1>�������ùٷ������ʼ�!������������ɼ������!</h1><h3><a href='http://localhost:8080/shops/user_active.action?code="+code+"'>http://localhost:8080/shops/user_active.action?code="+code+"</a></h3>", "text/html;charset=UTF-8");
			// 3.�����ʼ�:
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		sendMail("aaa@shop.com","11111111111111");
	}
}
