package cn.hellyuestc.caiyuan.service;

import java.util.Map;

public interface UserService {
	
	public Map<String, String> addUserByEmail(String email, String password, String confrimPassword);

}
