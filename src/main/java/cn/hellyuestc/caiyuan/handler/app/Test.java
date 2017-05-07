package cn.hellyuestc.caiyuan.handler.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hellyuestc.caiyuan.entity.CommentForPassage;
import cn.hellyuestc.caiyuan.entity.Passage;
import cn.hellyuestc.caiyuan.entity.User;

@Controller
@RequestMapping("/app")
public class Test {
	
	@RequestMapping("/test")
	@ResponseBody
	public List<User> test() {
		List<User> list = new ArrayList<>();
		User user = new User();
		list.add(user);
		return list;
	}

}
