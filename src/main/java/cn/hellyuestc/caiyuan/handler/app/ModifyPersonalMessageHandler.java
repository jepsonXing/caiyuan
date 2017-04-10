package cn.hellyuestc.caiyuan.handler.app;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hellyuestc.caiyuan.entity.User;
import cn.hellyuestc.caiyuan.service.CommonService;
import cn.hellyuestc.caiyuan.service.ModifyPersonalMessageService;

@Controller
@RequestMapping("/app")
public class ModifyPersonalMessageHandler {
	
	@Autowired
	private ModifyPersonalMessageService service;
	
	@Autowired
	private CommonService commonService;
	
	@RequestMapping(value="/modifyPersonalMessage", method=RequestMethod.POST)
	public @ResponseBody Map<String, String> excuteModifyPersonalMessage(User updateUser, @RequestParam("formatBirthday") String formatBirthday) {
		Map<String, String> message = new HashMap<String, String>();
		if (commonService.isUserExist(updateUser.getName())) {
			message.put("message", "用户已存在");
			return message;
		}
		
		service.updateUser(updateUser, formatBirthday);
		message.put("message", "修改成功");
		return message;
	}

}
