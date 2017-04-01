package cn.hellyuestc.caiyuan.dao;

import org.apache.ibatis.annotations.Param;

import cn.hellyuestc.caiyuan.entity.User;

public interface UserDao {

	User getUserByPhone(@Param("phone") String phone, @Param("password") String password);
	
	User getUserByEmail(@Param("email") String email,@Param("password")  String password);
	
	User getUserByName(@Param("name") String name, @Param("password") String password);
	
}
