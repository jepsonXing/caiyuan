package cn.hellyuestc.caiyuan.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class AnswerHandler {
	
	@RequestMapping("/")
	public String test() {
		return "test";
	}
	
}
