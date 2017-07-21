package cn.hellyuestc.caiyuan.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hellyuestc.caiyuan.dao.UserDao;
import cn.hellyuestc.caiyuan.entity.User;
import cn.hellyuestc.caiyuan.service.UserService;
import cn.hellyuestc.caiyuan.util.EmailSender;
import cn.hellyuestc.caiyuan.util.MyUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private JedisPool jedisPool;
	
	/*
	 * 根据id获取用户名
	 */
	@Override
	public String getNameById(long id) {
		return userDao.selectNameById(id);
	}
	
	/*
	 * 手机注册
	 */
	@Override
	public Map<String, Object> addUserByPhone(String phone, String verificationCode, String password, String confirmPassword) {
		Map<String, Object> map = new HashMap<>();
		Pattern pattern = null;
		Matcher matcher = null;
		
		// 密码格式错误，请输入6-20个字符
		pattern = Pattern.compile("^\\w{6,20}$");
		matcher = pattern.matcher(password);
		if (!matcher.matches()) {
			map.put("error", "密码格式错误，请输入1-20个字符的密码");
			return map;
		}
		
		// 密码不一致
		if (!password.equals(confirmPassword)) {
			map.put("error", "密码不一致");
			return map;
		}
		
		Jedis jedis = jedisPool.getResource();
		String phoneInRedis = jedis.get(verificationCode);
		
		// 验证码不存在
		if (phoneInRedis == null) {
			map.put("error", "验证码不存在");
			return map;
		}
		
		// 验证码非此手机号所请求
		if (!phoneInRedis.equals(phone)) {
			map.put("error", "验证码非此手机号所请求");
			return map;
		}
		
		jedis.del(verificationCode);
		jedisPool.returnResource(jedis);
		
		// 注册成功
		User user = new User();
		Date date = new Date();
		user.setName(phone);
		user.setAvatarUrl("userAvatars/default.png");
		user.setGender("男");
		user.setBirthday(MyUtil.formatDate(date, 0));
		user.setPhone(phone);
		user.setEmail("未绑定");
		user.setAddress("未填写");
		user.setJob("未填写");
		user.setIntroduction("Ta很懒，什么也没有留下~~");
		user.setFollowerCount(0);
		user.setFollowingCount(0);
		user.setStatus(1);
		user.setIsExpert((byte) 0);
		user.setGmtCreate(MyUtil.formatDate(date, 1));
		user.setGmtModified(MyUtil.formatDate(date, 1));
		
		long id = userDao.insertUserTypePhone(user);
		
		user.setId(id);
		map.put("user", user);
		return map;
	}
	
	/*
	 * 邮箱注册
	 */
	@Override
	public Map<String, String> addUserByEmail(String email, String password, String confirmPassword) {
		Map<String, String> map = new HashMap<>();
		Pattern pattern = null;
		Matcher matcher = null;
		
		// 邮箱格式错误
		pattern = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$");
		matcher = pattern.matcher(email);
		if (!matcher.matches()) {
			map.put("email-error", "邮箱格式错误");
			return map;
		}
		
		// 密码格式错误，请输入6-20个字符
		pattern = Pattern.compile("^\\w{6,20}$");
		matcher = pattern.matcher(password);
		if (!matcher.matches()) {
			map.put("password-error", "密码格式错误，请输入1-20个字符的密码");
			return map;
		}
		
		// 密码不一致
		if (!password.equals(confirmPassword)) {
			map.put("confrimPassword-error", "密码不一致");
			return map;
		}
		
		// 邮箱已被注册
		if (userDao.selectEmailCount(email) > 0) {
			map.put("email-error", "邮箱已注册");
			return map;
		}
		
		// 注册成功
		User user = new User();
		user.setName(email);
		user.setPassword(MyUtil.bcrypt(password));
		user.setAvatarUrl("userAvatars/default.png");
		user.setEmail(email);
		user.setActivationCode(MyUtil.createRandomCode());
		
		try {
			EmailSender.sendActivationLink(user.getActivationCode(), user.getEmail());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		userDao.insertUser(user);
		map.put("ok", "系统已向您的邮箱发送了一份邮件，验证后即可登录");
		return map;
		
	}

	/*
	 * 激活邮箱
	 */
	@Override
	public Map<String, String> activateEmail(String email, String activationCode) {
		Map<String, String> map = new HashMap<>();
		
		//邮箱未注册
		if (userDao.selectEmailCount(email) == 0) {
			map.put("email-error", "邮箱未注册");
			return map;
		}
		
		//邮箱已激活
		if (userDao.selectStatusByEmail(email) == 1) {
			map.put("activate-errot", "邮箱已激活");
			return map;
		}
		
		//激活码不正确
		if (!userDao.selectActivationCodeByEmail(email).equals(activationCode)) {
			map.put("activationCode-error", "激活码不正确");
			System.out.println();
			return map;
		}
		
		//激活成功
		userDao.updateUser(email, 1);
		map.put("ok", "激活成功");
		return map;
	}

	/*
	 * 更换用户头像
	 */
	@Override
	public void updateAvatarUrl(long id, String avatarUrl) {
		userDao.updateAvatarUrl(id, avatarUrl);
	}
	
	/*
	 * 手机验证重置密码
	 */
	@Override
	public Map<String, Object> resetPasswordByPhone(String phone, String verificationCode, String password, String confirmPassword) {
		Map<String, Object> map = new HashMap<>();
		Pattern pattern = null;
		Matcher matcher = null;
		
		// 密码格式错误，请输入6-20个字符
		pattern = Pattern.compile("^\\w{6,20}$");
		matcher = pattern.matcher(password);
		if (!matcher.matches()) {
			map.put("error", "密码格式错误，请输入1-20个字符的密码");
			return map;
		}
		
		// 密码不一致
		if (!password.equals(confirmPassword)) {
			map.put("error", "密码不一致");
			return map;
		}
		
		Jedis jedis = jedisPool.getResource();
		String phoneInRedis = jedis.get(verificationCode);
		
		// 验证码不存在
		if (phoneInRedis == null) {
			map.put("error", "验证码不存在");
			return map;
		}
		
		// 验证码非此手机号所请求
		if (!phoneInRedis.equals(phone)) {
			map.put("error", "验证码非此手机号所请求");
			return map;
		}
		
		jedis.del(verificationCode);
		jedisPool.returnResource(jedis);
		
		// 重置密码成功
		userDao.updatePassword(phone, MyUtil.bcrypt(password));
		
		map.put("ok", "重置密码成功");
		return map;
	}

}
