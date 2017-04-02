package cn.hellyuestc.caiyuan.handler;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.hellyuestc.caiyuan.entity.User;
import cn.hellyuestc.caiyuan.service.ModifyPersonalMessageService;

@Controller
public class ModifyPersonalMessageHandler {
	
	@Autowired
	private ModifyPersonalMessageService service;
	
	@RequestMapping(value="/excuteModifyPersonalMessage", method=RequestMethod.POST)
	public String excuteModifyPersonalMessage(User updateUser, @RequestParam("formatBirthday") String formatBirthday, HttpSession session) {
		User currentUser = (User) session.getAttribute("currentUser");
		currentUser = service.updateUser(updateUser, formatBirthday, currentUser);
		session.setAttribute("currentUser", currentUser);
		return "redirect:/myHomepage";
	}

}
