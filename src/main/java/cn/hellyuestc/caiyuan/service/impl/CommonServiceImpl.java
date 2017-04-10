package cn.hellyuestc.caiyuan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hellyuestc.caiyuan.dao.UserDao;

@Service
public class CommonServiceImpl implements cn.hellyuestc.caiyuan.service.CommonService {

	@Autowired
	UserDao userDao;
	
	@Override
	public boolean isUserExist(String name) {
		String result = userDao.getNameByName(name);
		if (result == null) {
			return false;
		} else {
			return true;
		}
	}

}
