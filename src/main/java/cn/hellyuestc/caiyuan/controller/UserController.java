package cn.hellyuestc.caiyuan.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hellyuestc.caiyuan.service.UserService;
import cn.hellyuestc.caiyuan.util.Response;
import cn.hellyuestc.caiyuan.util.Status;

@Controller
public class UserController {
	
	private Map<String, String> map;
	@Autowired
	private UserService userService;
	
	/*
	 * 邮箱注册
	 */
	@RequestMapping(value="/users", params={"type=email"}, method=RequestMethod.POST)
	@ResponseBody
	public Response registeByEmail(String email, String password, String confirmPassword) {
		Map<String, String> map = userService.addUserByEmail(email, password, confirmPassword);
		
		if (map.get("ok") != null) {
			return new Response(new Status(201, "系统已向您的邮箱发送了一份邮件，验证后即可登录"));
		} else {
			return new Response(new Status(400, "error"), map);
		}
	}
	
	/*
	 * 激活邮箱失败
	 */
	@RequestMapping("/acvateEmailError")
	@ResponseBody
	public Response acvateEmailError() {
		return new Response(new Status(400, "error"), map);
	}
	
	/*
	 * 激活邮箱
	 */
	@RequestMapping(value="/users/{email}/{activationCode}", method=RequestMethod.GET)
	public String activateEmail(@PathVariable String email, @PathVariable String activationCode) {
		Map<String, String> map = userService.activateEmail(email, activationCode);
		
		if (map.get("ok") != null) {
			return "activateEmailSuccess";
		} else {
			this.map = map;
			return "redirect:/acvateEmailError";
		}
	}

}