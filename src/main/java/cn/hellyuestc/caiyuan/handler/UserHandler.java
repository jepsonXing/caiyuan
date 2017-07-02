package cn.hellyuestc.caiyuan.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hellyuestc.caiyuan.service.UserService;
import cn.hellyuestc.caiyuan.util.Response;
import cn.hellyuestc.caiyuan.util.Status;

@Controller
@RequestMapping("/users")
public class UserHandler {
	
	@Autowired
	private UserService userService;
	
	/*
	 * 邮箱注册
	 */
	@RequestMapping(value="?type=email", method=RequestMethod.PUT)
	@ResponseBody
	public Response addUserByEmail(String email, String password, String confrimPassword) {
		Map<String, String> map = userService.addUserByEmail(email, password, confrimPassword);
		if (map.get("ok") == null) {
			return new Response(new Status(201, "系统已向您的邮箱发送了一份邮件，验证后即可登录"));
		} else {
			return new Response(new Status(400, "error"), map);
		}
	}

}
