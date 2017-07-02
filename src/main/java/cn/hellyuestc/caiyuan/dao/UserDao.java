package cn.hellyuestc.caiyuan.dao;

import cn.hellyuestc.caiyuan.entity.User;

public interface UserDao {
	
	int selectEmailCount(String email);
	
	void insertUser(User user);
	
}
