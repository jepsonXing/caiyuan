package cn.hellyuestc.caiyuan.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	public Response login(String account, String password) {
		Map<String, Object> map = sessionService.login(account, password);
		
		if (map.get("user") != null) {
			return new Response(new Status(201, "登录成功"), map);
		} else {
			return new Response(new Status(400, "error"), map);
		}
	}
	
	/*
	 * 登出
	 */
	@RequestMapping(value="/sessions/{session}", method=RequestMethod.DELETE)
	@ResponseBody
	public Response logout(@PathVariable String session) {
		Map<String, String> map = sessionService.logout(session);
		
		if (map.get("ok") != null) {
			return new Response(new Status(204, "登出成功"));
		} else {
			return new Response(new Status(404, "error"), map);
		}
	}
	
}