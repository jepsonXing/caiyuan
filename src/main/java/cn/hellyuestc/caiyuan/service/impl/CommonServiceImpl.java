package cn.hellyuestc.caiyuan.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.hellyuestc.caiyuan.service.CommonService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class CommonServiceImpl implements CommonService {
	
	@Autowired
	private JedisPool jedisPool;

	/*
	 * 检查用户是否已登录
	 */
	@Override
	public Map<String, String> getUserIdFromRedis(HttpServletRequest request) {
		Map<String, String> map = new HashMap<>();
		String token = null;
		
		System.out.println(request.getCookies().length);
		
		//遍历cookies查找是否存在token
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			map.put("cookie-error", "cookie为空");
			return map;
		}
		
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("token")) {
				token = cookie.getValue();
				break;
			}
		}
		
		//token不存在，未登录
		if (token == null) {
			map.put("token-error", "token不存在");
			return map;
		}
		
		//验证token是否正确
		Jedis jedis = jedisPool.getResource();
		String userId = jedis.get(token);
		jedisPool.returnResource(jedis);
		//token不正确，Redis中找不到相应键值对
		if (userId == null) {
			map.put("token-error", "无效的token");
			return map;
		}
		//token正确，找到对应的键值对
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

}
