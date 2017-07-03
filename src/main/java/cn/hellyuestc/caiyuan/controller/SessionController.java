package cn.hellyuestc.caiyuan.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hellyuestc.caiyuan.service.SessionService;
import cn.hellyuestc.caiyuan.util.Response;
import cn.hellyuestc.caiyuan.util.Status;

@Controller
public class SessionController {
	
	@Autowired
	private SessionService sessionService;

	@RequestMapping(value="/sessions", method=RequestMethod.POST)
	@ResponseBody
	public Response login(String account, String password, HttpServletResponse response) {
		Map<String, Object> map = sessionService.login(account, password, response);
		if (map.get("user") != null) {
			return new Response(new Status(201, "登录成功"), map);
		} else {
			return new Response(new Status(400, "error"), map);
		}
	}
	
}