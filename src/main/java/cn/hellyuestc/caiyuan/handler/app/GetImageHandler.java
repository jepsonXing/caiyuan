package cn.hellyuestc.caiyuan.handler.app;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/app")
@Controller
public class GetImageHandler {

	@RequestMapping(value="/getAvatar/{username}", method=RequestMethod.GET)
	public Map<String, byte[]> getAvatar(@PathVariable String username) {
		
		return null;
	}
	
}
