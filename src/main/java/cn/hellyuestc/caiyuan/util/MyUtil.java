package cn.hellyuestc.caiyuan.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.multipart.MultipartFile;

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
	
	public static String formatDate(Date date, int type) {
		SimpleDateFormat sdf = null;
		if (type == 0) {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		} else {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
		return sdf.format(date);
	}
	
	/*
	 * 判断上传的文件是否是符合要求的图片
	 * 支持的图片格式：.bmp、.jpg、.jpeg、.png、.gif
	 */
	public static boolean isLegalImage(MultipartFile file) {
		Pattern pattern = Pattern.compile("(?i).+?\\.(bmp|jpg|jpeg|png|gif)");
		Matcher matcher = pattern.matcher(file.getOriginalFilename());
		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}
	
}
