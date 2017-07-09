package cn.hellyuestc.caiyuan.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public interface UserService {
	
	/*
	 * 根据id获取用户名
	 */
	public String getNameById(long id);
	
	/*
	 * 邮箱注册
	 */
	public Map<String, String> addUserByEmail(String email, String password, String confirmPassword);

	/*
	 * 激活邮箱
	 */
	public Map<String, String> activateEmail(String email, String activationCode);
	
	/*
	 * 更换用户头像
	 */
	public void updateAvatarUrl(long id, String avatarUrl);
}
