package cn.hellyuestc.caiyuan.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hellyuestc.caiyuan.dao.UserDao;
import cn.hellyuestc.caiyuan.entity.User;
import cn.hellyuestc.caiyuan.service.SessionService;
import cn.hellyuestc.caiyuan.util.MyUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class SessionServiceImpl implements SessionService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private JedisPool jedisPool; 

	/*
	 * 检查账号是否存在
	 */
	@Override
	public long validateAccount(String account) {
		long userId = 0;

		// 当账号是手机号时
		userId = userDao.selectIdByPhone(account);
		if (userId != 0) {
			return userId;
		}

		// 当账号是邮箱时
		userId = userDao.selectIdByEmail(account);
		if (userId != 0) {
			return userId;
		}

		return userId;
	}

	/*
	 * 登录
	 */
	@Override
	public Map<String, Object> login(String account, String password) {
		Map<String, Object> map = new HashMap<>();
		long userId = 0;

		// 账号不存在
		userId = validateAccount(account);
		if (userId == 0) {
			map.put("account-error", "账号不存在");
			return map;
		}

		// 密码不正确
		if (!MyUtil.checkPassword(password, userDao.selectPasswordById(userId))) {
			map.put("password-error", "密码不正确");
			return map;
		}

		// 账号未激活
		if (userDao.selectStatusById(userId) == 0) {
			map.put("status-error", "账号未激活");
			return map;
		}

		// 登录成功
		// 设置登录cookie
		String token = MyUtil.createRandomCode();
		map.put("taken", token);

		// 将token:userId存入redis，并设置过期时间
		Jedis jedis = jedisPool.getResource();
		jedis.set(token, Long.toString(userId), "NX", "EX", 60 * 60 * 24 * 30);
		jedisPool.returnResource(jedis);
		
		//返回用户信息
		User user = userDao.selectUserById(userId);
		map.put("user", user);
		return map;
		
	}

	/*
	 * 登出
	 */
	@Override
	public Map<String, String> logout(String session) {
		Map<String, String> map = new HashMap<>();
		
		//删除session
		Jedis jedis = jedisPool.getResource();
		long result = jedis.del(session);
		jedisPool.returnResource(jedis);
		
		//判断session是否删除成功
		if (result == 0) {
			map.put("session-error", "不存在此会话");
			return map;
		} else {
			map.put("ok", "删除会话成功");
			return map;
		}
	}

}