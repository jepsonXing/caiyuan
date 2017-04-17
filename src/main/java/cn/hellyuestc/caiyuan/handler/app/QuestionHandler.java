package cn.hellyuestc.caiyuan.handler.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hellyuestc.caiyuan.entity.Question;
import cn.hellyuestc.caiyuan.service.QuestionService;

@Controller
@RequestMapping("/app/question")
public class QuestionHandler {
	
	@Autowired
	QuestionService questionService;
	
	@RequestMapping(value="/new/getPrePage/{lastRefreshTime}", method=RequestMethod.GET)
	public @ResponseBody List<Question> getPrePageNew(@PathVariable String lastRefreshTime) {
		return questionService.getPrePageNew(lastRefreshTime);
	}
	
	@RequestMapping(value="/new/getNextPage/{lastViewTime}", method=RequestMethod.GET)
	public @ResponseBody List<Question> getNextPageNew(@PathVariable String lastViewTime) {
		return questionService.getNextPageNew(lastViewTime);
	}
	
	@RequestMapping(value="/hot/getNextPage/{pageNumber}", method=RequestMethod.GET)
	public @ResponseBody List<Question> getNextPageHot(@PathVariable int pageNumber) {
		
		return null;
	}
	
}
