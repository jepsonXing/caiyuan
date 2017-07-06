package cn.hellyuestc.caiyuan.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

	/*
	 * 登录
	 */
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
	
	/*
	 * 登出
	 */
	@RequestMapping(value="/sessions", method=RequestMethod.DELETE)
	@ResponseBody
	public Response logout(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> map = sessionService.logout(request, response);
		
		if (map.get("ok") != null) {
			return new Response(new Status(204, "登出成功"));
		} else {
			return new Response(new Status(404, "error"), map);
		}
	}
	
}