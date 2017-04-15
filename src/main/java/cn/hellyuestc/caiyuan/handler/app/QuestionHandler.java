package cn.hellyuestc.caiyuan.handler.app;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hellyuestc.caiyuan.entity.Question;

@Controller
@RequestMapping("/app")
public class QuestionHandler {
	
	@RequestMapping(value="/question/new/getPrePage/{lastRefreshTime}", method=RequestMethod.GET)
	public @ResponseBody List<Question> getPrePageNew(@PathVariable Date lastRefreshTime) {
		
		return null;
	}
	
	@RequestMapping(value="/question/hot/getNextPage/1", method=RequestMethod.GET)
	public @ResponseBody List<Question> getNextPageHot(@PathVariable int pageNumber) {
		
		return null;
	}
	
}
