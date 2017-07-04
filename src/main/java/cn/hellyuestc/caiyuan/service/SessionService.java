package cn.hellyuestc.caiyuan.service;

import java.util.Map;

public interface SessionService {
	
	/*
	 * 检查账号是否存在
	 */
	public long validateAccount(String account);
	
	/*
	 * 登录
	 */
	public Map<String, Object> login(String account, String password);
	
	/*
	 * 登出
	 */
	public Map<String, String> logout(String session);
	
}