package cn.hellyuestc.caiyuan.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import cn.hellyuestc.caiyuan.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService {

	@Override
	public Map<String, String> getVerificationCode(String phone) {
		Map<String, String> map = new HashMap<>();
		
		//手机号格式错误
		Pattern pattern = Pattern.compile("^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$");
		Matcher matcher = pattern.matcher(phone);
		if (!matcher.matches()) {
			map.put("phone-error", "手机号格式不正确");
			return map;
		}
		
		//验证码发送成功
		return null;
		
		
	}

}
