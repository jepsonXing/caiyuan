package cn.hellyuestc.caiyuan.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.mindrot.jbcrypt.BCrypt;

public class MyUtil {
	
	private static FileInputStream caiyuanIS;
	
	static {
		try {
			caiyuanIS = new FileInputStream(MyUtil.class.getClassLoader().getResource("caiyuan.properties").getPath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * bcrypt加密
	 */
	public static String bcrypt(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	/*
	 * 产生随机数作为邮箱验证激活码
	 */
	public static String createRandomCode() {
		return new Date().getTime() + UUID.randomUUID().toString().replace("-", "");
	}
	
	/*
	 * 获取caiyuan.properties配置文件对象
	 */
	public static Properties getCaiyuanProperties() {
		Properties caiyuanProperties = new Properties();
		try {
			caiyuanProperties.load(caiyuanIS);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return caiyuanProperties;
	}

	//javax.mail发送邮件
	public static void sendEamil(String code, String email, int operation) throws MessagingException {
		Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.163.com");
        props.put("mail.smtp.auth", "true");
        //基本的邮件会话
        Session session = Session.getInstance(props);
        //构造信息体
        MimeMessage message = new MimeMessage(session); 
        //发件地址
        Address address = new InternetAddress("18483661669@163.com");
        message.setFrom(address);
        //收件地址
        Address toAddress = new InternetAddress(email);
        message.setRecipient(MimeMessage.RecipientType.TO, toAddress);
        
        //主题
        message.setSubject("菜缘");
        //正文
        String text = "<html><head></head><body>";
        if (operation == 1) {
        	text += "<a href='https://www.hellyuestc.cn/activate?code=" + code + "' >点击激活您的邮箱</a></body>";
		} else {
//			sb.append("是否将您的密码修改为:");
//			sb.append(code.substring(0, 8));
//			sb.append("，<a href=" + MyConstant.DOMAIN_NAME + "verify.do?code=" + code + ">");
//			sb.append("点击是</a></body>");
		}
        
        message.setContent(text,"text/html;charset=UTF-8");
                
        message.saveChanges();
        session.setDebug(true);
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.163.com", "18483661669@163.com", "xingyi2015");
        //发送
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
	}
	
}
