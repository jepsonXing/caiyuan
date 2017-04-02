package cn.hellyuestc.caiyuan.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageHandler {
	
	@RequestMapping("/modifyPersonalMessage")
	public String modifyPersonalMessage() {
		return "modifyPersonalMessage";
	}

}
