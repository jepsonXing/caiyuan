package cn.hellyuestc.caiyuan.util;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
	
	private static Session session;
	private static MimeMessage message;
	private static String text;
	
	private static void beforeSend(String email) throws AddressException, MessagingException {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", MyConstant.MAIL_HOST);
		properties.put("mail.smtp.auth", MyConstant.MAIL_AUTH);
        //基本的邮件会话
        session = Session.getInstance(properties);
        //构造信息体
        message = new MimeMessage(session); 
        //发件地址
        message.setFrom(new InternetAddress(MyConstant.MAIL_USERNAME));
        //收件地址
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email));
	}
	
	private static void executeSend() throws MessagingException {
        message.saveChanges();
        session.setDebug(true);
        Transport transport = session.getTransport("smtp");
        transport.connect(MyConstant.MAIL_HOST, MyConstant.MAIL_USERNAME, MyConstant.MAIL_PASSWORD);
        //发送
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
	}
	
	/*
	 * 发送邮箱激活链接
	 */
	public static void sendActivationLink(String activationCode, String email) throws MessagingException {
		beforeSend(email);
		text = "<html><head></head><body><a href='https://www.hellyuestc.cn/users/" + email + "/" + activationCode + "' >点击激活您的邮箱</a></body>";
		message.setSubject("菜缘");
        message.setContent(text,"text/html;charset=UTF-8");
        executeSend();
	}
	
}
