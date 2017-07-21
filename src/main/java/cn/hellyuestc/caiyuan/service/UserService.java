package cn.hellyuestc.caiyuan.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public interface UserService {
	
	/*
	 * 根据id获取用户名
	 */
	String getNameById(long id);
	
	/*
	 * 手机注册
	 */
	Map<String, Object> addUserByPhone(String phone, String verificationCode, String password, String confirmPassword, HttpServletResponse response);
	
	/*
	 * 邮箱注册
	 */
	Map<String, String> addUserByEmail(String email, String password, String confirmPassword);

	/*
	 * 激活邮箱
	 */
	Map<String, String> activateEmail(String email, String activationCode);
	
	/*
	 * 更换用户头像
	 */
	void updateAvatarUrl(long id, String avatarUrl);
	
	/*
	 * 手机验证重置密码
	 */
	Map<String, Object> resetPasswordByPhone(String phone, String verificationCode, String password, String confirmPassword);
	
}
