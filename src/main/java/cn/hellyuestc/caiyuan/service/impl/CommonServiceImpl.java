package cn.hellyuestc.caiyuan.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.hellyuestc.caiyuan.service.CommonService;
import cn.hellyuestc.caiyuan.util.VerificationCodeSender;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	private JedisPool jedisPool;

	/*
	 * 获取手机验证码
	 */
	@Override
	public Map<String, Object> getVerificationCode(String phone) {
		Map<String, Object> map = new HashMap<>();
		
		// 手机号格式不正确
		Pattern pattern = Pattern.compile("^(13[0-9]|14[579]|15[0-3,5-9]|17[0135678]|18[0-9])\\d{8}$");
		Matcher matcher = pattern.matcher(phone);
		if (!matcher.matches()) {
			map.put("error", "手机号格式错误");
			return map;
		}
		
		// 手机号格式正确，发送验证码
		String verificationCode = VerificationCodeSender.sendVerificationCode(phone);
		Jedis jedis = jedisPool.getResource();
		jedis.set(verificationCode, phone, "NX", "EX", 60 * 5);
		jedisPool.returnResource(jedis);
		
		map.put("ok", "验证码已发送");
		return map;
	}
	
	/*
	 * 检查用户是否已登录
	 */
	@Override
	public Map<String, Object> getUserIdFromRedis(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		String token = null;

		// 遍历cookies查找是否存在token
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			map.put("error", "cookie为空");
			return map;
		}

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("token")) {
				token = cookie.getValue();
				break;
			}
		}

		// token不存在，未登录
		if (token == null) {
			map.put("error", "token不存在");
			return map;
		}

		// 验证token是否正确
		Jedis jedis = jedisPool.getResource();
		String userId = jedis.get(token);
		jedisPool.returnResource(jedis);
		// token不正确，Redis中找不到相应键值对
		if (userId == null) {
			map.put("error", "无效的token");
			return map;
		}
		// token正确，找到对应的键值对
		map.put("userId", userId);
		return map;
	}

	@Override
	public void putImageToLocal(MultipartFile image, String localPath, String LocalImageName) {
		File localImage = new File(localPath, LocalImageName);
		try {
			image.transferTo(localImage);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, Object> validateTimeAndCount(String time, int count) {
		Map<String, Object> map = new HashMap<>();

		// 时间格式不正确
		Pattern p = Pattern.compile(
				"^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$",
				Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
		Matcher m = p.matcher(time.trim());
		if (!m.find()) {
			map.put("error", "请输入yyyy-MM-dd HH:MM:SS格式的时间戳");
			return map;
		}

		// count不合法
		if ((count < 1) || (20 < count)) {
			map.put("error", "count的范围需为1-20");
			return map;
		}

		// time和count均合法
		map.put("ok", "time和count均合法");
		return map;
	}

}
