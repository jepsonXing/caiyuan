package cn.hellyuestc.caiyuan.util;

import java.util.Date;
import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;

public class MyUtil {
	
	/*
	 * bcrypt加密
	 */
	public static String bcrypt(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	public static boolean checkPassword(String password, String hash) {
		return BCrypt.checkpw(password, hash);
	}
	
	/*
	 * 产生随机数作为邮箱验证激活码
	 */
	public static String createRandomCode() {
		return new Date().getTime() + UUID.randomUUID().toString().replace("-", "");
	}
	
}
