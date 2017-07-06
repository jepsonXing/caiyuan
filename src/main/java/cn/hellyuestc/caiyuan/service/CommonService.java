package cn.hellyuestc.caiyuan.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface CommonService {
	
	/*
	 * 检查用户是否已登录
	 */
	public Map<String, String> getUserIdFromRedis(HttpServletRequest request);
}
