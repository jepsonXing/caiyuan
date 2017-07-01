package cn.hellyuestc.caiyuan.util;

import java.util.Date;
import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;

public class MyUtil {
	
	public static String bcrypt(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	public static String createRandomCode() {
		return new Date().getTime() + UUID.randomUUID().toString().replace("-", "");
	}

}
