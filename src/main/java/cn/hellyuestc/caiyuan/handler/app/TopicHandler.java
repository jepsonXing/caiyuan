package cn.hellyuestc.caiyuan.handler.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hellyuestc.caiyuan.entity.Topic;
import cn.hellyuestc.caiyuan.service.TopicService;

@Controller
@RequestMapping("/app")
public class TopicHandler {
	
	@Autowired
	private TopicService topicService;

	@RequestMapping(value="/topic/getNextPage/{pageNumber}", method=RequestMethod.GET)
	public @ResponseBody List<Topic> getNextPage(@PathVariable int pageNumber) {
		return topicService.listNextPage(pageNumber);
	}
	
}
