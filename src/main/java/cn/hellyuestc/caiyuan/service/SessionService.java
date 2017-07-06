package cn.hellyuestc.caiyuan.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SessionService {
	
	/*
	 * 检查账号是否存在
	 */
	public long validateAccount(String account);
	
	/*
	 * 登录
	 */
	public Map<String, Object> login(String account, String password, HttpServletResponse response);
	
	/*
	 * 登出
	 */
	public Map<String, String> logout(HttpServletRequest request, HttpServletResponse response);
	
}