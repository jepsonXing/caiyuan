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
import cn.hellyuestc.caiyuan.util.MyConstant;
import cn.hellyuestc.caiyuan.util.MyUtil;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public Map<String, String> addUserByEmail(String email, String password, String confrimPassword) {
		Map<String, String> map = new HashMap<>();
		Pattern pattern = null;
		Matcher matcher = null;
		
		/*
		 * 邮箱格式错误
		 */
		pattern = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$");
		matcher = pattern.matcher(email);
		if (!matcher.matches()) {
			map.put("email-error", "邮箱格式错误");
			return map;
		}
		
		/*
		 * 密码格式错误，请输入6-20个字符
		 */
		pattern = Pattern.compile("^\\w{6,20}$");
		matcher = pattern.matcher(password);
		if (!matcher.matches()) {
			map.put("password-error", "请输入1-20个字符的密码");
			return map;
		}
		
		/*
		 * 密码不一致
		 */
		if (!password.equals(confrimPassword)) {
			map.put("confrimPassword-error", "密码不一致");
			return map;
		}
		
		/*
		 * 邮箱已被注册
		 */
		if (userDao.selectEmailCount(email) > 0) {
			map.put("email-error", "邮箱已注册");
			return map;
		}
		
		/*
		 * 成功注册
		 */
		User user = new User();
		user.setName(email);
		user.setPassword(MyUtil.bcrypt(password));
		user.setAvatarUrl(MyConstant.QINIU_IMAGE_URL + "user_avatar/default.png");
		user.setBirthday(new Date());
		user.setEmail(email);
		user.setActivationCode(MyUtil.createRandomCode());
		user.setGmtCreate(new Date());
		user.setGmtModified(new Date());
		
		try {
			EmailSender.sendActivationLink(user.getActivationCode(), user.getEmail());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		userDao.insertUser(user);
		map.put("ok", "系统已向您的邮箱发送了一份邮件，验证后即可登录");
		return map;
		
	}

}
