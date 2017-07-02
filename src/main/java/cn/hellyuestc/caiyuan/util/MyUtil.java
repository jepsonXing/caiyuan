package cn.hellyuestc.caiyuan.util;

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
	
}
