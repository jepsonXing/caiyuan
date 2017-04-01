package cn.hellyuestc.caiyuan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hellyuestc.caiyuan.dao.UserDao;
import cn.hellyuestc.caiyuan.entity.User;
import cn.hellyuestc.caiyuan.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private UserDao userDao;
	
	private User currentUser = null;

	@Override
	public User validateLoginByPhone(String phone, String password) {
		currentUser = userDao.getUserByPhone(phone, password);
		return currentUser;
	}

	@Override
	public User validateLoginByEmail(String email, String password) {
		currentUser = userDao.getUserByEmail(email, password);
		return currentUser;
	}
	
	@Override
	public User validateLoginByName(String name, String password) {
		currentUser = userDao.getUserByName(name, password);
		return currentUser;
	}

}
