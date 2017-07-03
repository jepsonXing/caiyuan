package cn.hellyuestc.caiyuan.dao;

import cn.hellyuestc.caiyuan.entity.User;

public interface UserDao {
	
	int selectEmailCount(String email);
	
	void insertUser(User user);
	
	int selectStatusByEmail(String email);
	
	String selectActivationCodeByEmail(String email);
	
	void updateUser(int status);
	
	long selectIdByPhone(String phone);
	
	long selectIdByEmail(String email);
	
	byte selectStatusById(long id);
	
	String selectPasswordById(long id);
	
	User selectUserById(long id);
	
}
