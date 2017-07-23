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
	
	/*
	 * 发布回答
	 */
	@RequestMapping(value="/questions/{id}/answers", method=RequestMethod.POST)
	public @ResponseBody Response publishAnswer(@PathVariable("id") long questionId, String content, HttpServletRequest request) {
		Map<String, Object> map = answerService.addAnswer(questionId, content, 1, request);
		if (map.get("answer") == null) {
			return new Response(new Status(400, "error"), map);
		} else {
			return new Response(new Status(201, "发布回答成功"), map);
		}
	}
	
	/*
	 * 保存回答草稿
	 */
	@RequestMapping(value="/questions/{id}/answerDrafts", method=RequestMethod.POST)
	public @ResponseBody Response saveAnswerDraft(@PathVariable("id") long questionId, String content, HttpServletRequest request) {
		Map<String, Object> map = answerService.addAnswer(questionId, content, 0, request);
		if (map.get("answer") == null) {
			return new Response(new Status(400, "error"), map);
		} else {
			return new Response(new Status(201, "保存回答成功"), map);
		}
	}
	
	/*
	 * 更新回答
	 */
	@RequestMapping(value="/answers/{id}", method=RequestMethod.POST)
	public @ResponseBody Response updateAnswer(@PathVariable("id") long answerId, String content, HttpServletRequest request) {
		Map<String, Object> map = answerService.updateAnswer(answerId, content, request);
		if (map.get("answer") == null) {
			return new Response(new Status(400, "error"), map);
		} else {
			return new Response(new Status(201, "更新回答成功"), map);
		}
	}
	
	/*
	 * 更新回答草稿
	 */
	@RequestMapping(value="/answerDrafts/{id}", method=RequestMethod.POST)
	public @ResponseBody Response updateAnswerDraft(@PathVariable("id") long answerId, String content, HttpServletRequest request) {
		return updateAnswer(answerId, content, request);
	}
	
	/*
	 * 发布回答草稿
	 */
	@RequestMapping(value="/answerDrafts/{id}", params={"field=isPublish"}, method=RequestMethod.GET)
	public @ResponseBody Response publishAnswerDraft(@PathVariable("id") long answerDraftId, HttpServletRequest request) {
		
		
		
		return null;
	}
	
	/*
	 * 获取回答（）
	 */
	@RequestMapping(value="/questions/{id}/answers", params={"offset", "limit", "type=new"}, method=RequestMethod.GET)
	public @ResponseBody Response getNewAnswersByTime(@PathVariable("id") long questionId, @RequestParam("offset") String time, @RequestParam("limit") int count) {
		Map<String, Object> map = new HashMap<>();
		
		//检查时间和请求的问题数量是否合法
		map = commonService.validateTimeAndCount(time, count);
 		if (map.get("ok") == null) {
 			return new Response(new Status(400, "error"), map);
 		}
 		
 		map = answerService.getAnswers(questionId, time, count, "new");
 		return new Response(new Status(200, "ok"), map);
	}

}
