package cn.hellyuestc.caiyuan.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import cn.hellyuestc.caiyuan.entity.User;

public interface UserDao {
	
	int selectEmailCount(String email);
	
	void insertUser(User user);
	
	
	

	User getUserByPhone(@Param("phone") String phone, @Param("password") String password);
	
	User getUserByEmail(@Param("email") String email,@Param("password")  String password);
	
	User getUserByName(@Param("name") String name, @Param("password") String password);
	
	String getNameByName(String name);
	
	void updateUser(@Param("id") long id, @Param("name") String name, @Param("gender") String gender, @Param("birthday") Date birthday,
			@Param("phone") String phone, @Param("email") String email, @Param("address") String address, @Param("job") String job, 
			@Param("introduction") String introduction, @Param("gmt_modified") Date gmtModified);
	
}
