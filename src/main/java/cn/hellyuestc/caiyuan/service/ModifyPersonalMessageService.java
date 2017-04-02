package cn.hellyuestc.caiyuan.service;

import cn.hellyuestc.caiyuan.entity.User;

public interface ModifyPersonalMessageService {

	User updateUser(User updageUser, String formatBirthday, User currentUser);
	
}
