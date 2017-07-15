package cn.hellyuestc.caiyuan.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hellyuestc.caiyuan.service.AnswerService;
import cn.hellyuestc.caiyuan.service.CommonService;
import cn.hellyuestc.caiyuan.util.Response;
import cn.hellyuestc.caiyuan.util.Status;

@Controller
public class AnswerController {
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private AnswerService answerService;
	
	@RequestMapping(value="/questions/{id}/answers", method=RequestMethod.POST)
	public @ResponseBody Response addAnswer(@PathVariable("id") long questionId, String content, HttpServletRequest request) {
		Map<String, Object> map = answerService.addAnswer(questionId, content, request);
		if (map.get("answer") == null) {
			return new Response(new Status(201, "创建问题成功"), map);
		} else {
			return new Response(new Status(400, "error"), map);
		}
	}
	
	@RequestMapping(value="/questions/{id}/answers", params={"offset", "limit", "type=new"}, method=RequestMethod.GET)
	public @ResponseBody Response getNewAnswersByTime(@PathVariable("id") long questionId, @RequestParam("offset") String time, @RequestParam("limit") int count) {
		Map<String, Object> map = new HashMap<>();
		
		//检查时间和请求的问题数量是否合法
		map = commonService.validateTimeAndCount(time, count);
 		if (map.get("ok") == null) {
 			return new Response(new Status(400, "error"), map);
 		}
 		
 		map = answerService.getQuestions(questionId, time, count, "new");
 		return new Response(new Status(200, "ok"), map);
	}

}
