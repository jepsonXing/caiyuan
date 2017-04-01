package cn.hellyuestc.caiyuan.service;

import cn.hellyuestc.caiyuan.entity.User;

public interface LoginService {

	User validateLoginByPhone(String phone, String password);
	
	User validateLoginByEmail(String email, String password);
	
	User validateLoginByName(String name, String password);
	
}
