package cn.hellyuestc.caiyuan.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hellyuestc.caiyuan.dao.UserDao;
import cn.hellyuestc.caiyuan.entity.User;
import cn.hellyuestc.caiyuan.service.ModifyPersonalMessageService;

@Service
public class ModifyPersonalMessageServiceImpl implements ModifyPersonalMessageService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public User updateUser(User updateUser, String formatBirthday, User currentUser) {
		updateUser.setId(currentUser.getId());
		updateUser.setAvatarPath(currentUser.getAvatarPath());
		updateUser.setIsExpert(currentUser.getIsExpert());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			updateUser.setBirthday(format.parse(formatBirthday));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		userDao.updateUser(updateUser.getId(), updateUser.getName(), updateUser.getGender(), updateUser.getBirthday(),
				updateUser.getPhone(), updateUser.getEmail(), updateUser.getAddress(), updateUser.getJob(), updateUser.getIntroduction());
		
		return updateUser;
	}

}
