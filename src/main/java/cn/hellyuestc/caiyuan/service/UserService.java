package cn.hellyuestc.caiyuan.service;

import java.util.Map;

public interface UserService {
	
	/*
	 * 邮箱注册
	 */
	public Map<String, String> addUserByEmail(String email, String password, String confirmPassword);

	/*
	 * 激活邮箱
	 */
	public Map<String, String> activateEmail(String email, String activationCode);
	
}
