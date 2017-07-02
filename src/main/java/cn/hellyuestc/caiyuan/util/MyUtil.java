package cn.hellyuestc.caiyuan.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;

public class MyUtil {
	
	private static FileInputStream caiyuanIS;
	
	static {
		try {
			caiyuanIS = new FileInputStream("classpath:caiyuan.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static String bcrypt(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	public static String createRandomCode() {
		return new Date().getTime() + UUID.randomUUID().toString().replace("-", "");
	}
	
	public static Properties getCaiyuanProperties() {
		Properties caiyuanProperties = new Properties();
		try {
			caiyuanProperties.load(caiyuanIS);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return caiyuanProperties;
	}

}
