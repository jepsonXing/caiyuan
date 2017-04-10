package cn.hellyuestc.caiyuan.handler.app;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hellyuestc.caiyuan.entity.User;
import cn.hellyuestc.caiyuan.service.LoginService;

@Controller
@RequestMapping("/app")
public class LoginHandler {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="/validateLogin", method=RequestMethod.POST)
	public @ResponseBody User validateLoginForAPP(@RequestParam("account") String account, @RequestParam("password") String password, HttpSession session) {
		User currentUser = null;
		if (null != (currentUser = loginService.validateLoginByPhone(account, password))) {
			return currentUser;
		} else if (null != (currentUser = loginService.validateLoginByEmail(account, password))) {
			session.setAttribute("currentUser", currentUser);
			return currentUser;
		} else if (null != (currentUser = loginService.validateLoginByName(account, password))) {
			session.setAttribute("currentUser", currentUser);
			return currentUser;
		} else {
			return currentUser;
		}
	}

}
