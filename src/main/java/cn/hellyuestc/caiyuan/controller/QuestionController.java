package cn.hellyuestc.caiyuan.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hellyuestc.caiyuan.service.CommonService;
import cn.hellyuestc.caiyuan.service.QuestionService;
import cn.hellyuestc.caiyuan.util.Response;
import cn.hellyuestc.caiyuan.util.Status;

@Controller
public class QuestionController {
	
	@Autowired
	private CommonService commonService;
	@Autowired
	private QuestionService questionService;
	
	@RequestMapping(value="/questions", method=RequestMethod.POST)
	@ResponseBody
	public Response addQuestion() {
		
		return null;
	}
	
	@RequestMapping(value="/questions", params={"offset={time}", "limit={count}", "type=new"}, method=RequestMethod.GET)
	@ResponseBody
	public Response getNewQuestionsByTime(@PathVariable String time, @PathVariable int count, HttpServletRequest request) {
		Map<String, String> stringMap = new HashMap<>();
		Map<String, Object> objectMap = new HashMap<>();
 		long userId = 0;
 		
 		//检查时间和请求的问题数量是否合法
 		stringMap = questionService.validateTimeAndCount(time, count);
 		if (stringMap.get("ok") == null) {
 			return new Response(new Status(400, "error"), stringMap);
 		}

 		stringMap = commonService.getUserIdFromRedis(request);
		
		// 未登录
		if (stringMap.get("userId") == null) {
			// 游客，返回所有话题中的问题
			objectMap = questionService.getQuestions(time, count, "new");
		}

		userId = Long.parseLong((String) stringMap.get("userId"));
		
		objectMap = questionService.getQuestions(userId, time, count, "new");
		
		return new Response(new Status(200, "ok"), objectMap);
	}
	
	@RequestMapping(value="/questions", params={"offset={time}", "limit={count}", "type=next"}, method=RequestMethod.GET)
	@ResponseBody
	public Response getNextQuestionsByTime(@PathVariable String time, @PathVariable int count, HttpServletRequest request) {
		Map<String, String> stringMap = new HashMap<>();
		Map<String, Object> objectMap = new HashMap<>();
 		long userId = 0;
 		
 		//检查时间和请求的问题数量是否合法
 		stringMap = questionService.validateTimeAndCount(time, count);
 		if (stringMap.get("ok") == null) {
 			return new Response(new Status(400, "error"), stringMap);
 		}

 		stringMap = commonService.getUserIdFromRedis(request);
		
		// 未登录
		if (stringMap.get("userId") == null) {
			// 游客，返回所有话题中的问题
			objectMap = questionService.getQuestions(time, count, "next");
		}

		userId = Long.parseLong((String) stringMap.get("userId"));
		
		objectMap = questionService.getQuestions(userId, time, count, "next");
		
		return new Response(new Status(200, "ok"), objectMap);
	}

}