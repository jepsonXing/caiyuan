package cn.hellyuestc.caiyuan.service;

import java.util.Map;

public interface CommonService {
	
	/*
	 * 获取手机验证码
	 */
	public Map<String, String> getVerificationCode(String phone);

}
