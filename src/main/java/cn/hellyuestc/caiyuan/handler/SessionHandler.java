package cn.hellyuestc.caiyuan.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.hellyuestc.caiyuan.util.Response;

@Controller
public class SessionHandler {
	
	@RequestMapping(value="/sessions", method=RequestMethod.POST)
	public Response loginByAccount() {
		return null;
	}

}
