package cn.hellyuestc.caiyuan.controller;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hellyuestc.caiyuan.service.AnswerCommentService;
import cn.hellyuestc.caiyuan.util.Response;
import cn.hellyuestc.caiyuan.util.Status;

@Controller
public class AnswerCommentController {
	
	@Autowired
	private AnswerCommentService answerCommentService;

	@RequestMapping(value="/answers/{id}/comments", method=RequestMethod.POST)
	public @ResponseBody Response addAnswerComment(@PathVariable("id") long answerId, long fromUserId, long toUserId, String content) {
		Map<String, Object> map = answerCommentService.addAnswerComment(answerId, fromUserId, toUserId, content);
		if (map.get("answerComment") == null) {
			return new Response(new Status(201, "创建评论成功"), map);
		} else {
			return new Response(new Status(400, "error"), map);
		}
	}
	
	@RequestMapping(value="/answers/{id}/comments", params={"offset", "limit"}, method=RequestMethod.GET)
	public @ResponseBody Response getAnswerComments(@PathVariable("id") long answerId, @Param("offset") String time, @Param("limit") int count) {
		Map<String, Object> map = answerCommentService.getAnswerComments(answerId, time, count);
		if (map.get("answerComments") == null) {
			return new Response(new Status(400, "error"), map);
		} else {
			return new Response(new Status(200, "ok"), map);
		}
	}
	
}
