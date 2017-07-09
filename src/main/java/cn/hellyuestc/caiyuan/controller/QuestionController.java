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
import org.springframework.web.multipart.MultipartFile;

import cn.hellyuestc.caiyuan.entity.Question;
import cn.hellyuestc.caiyuan.service.CommonService;
import cn.hellyuestc.caiyuan.service.QuestionService;
import cn.hellyuestc.caiyuan.service.TopicService;
import cn.hellyuestc.caiyuan.service.UserService;
import cn.hellyuestc.caiyuan.util.ImageUtil;
import cn.hellyuestc.caiyuan.util.MyUtil;
import cn.hellyuestc.caiyuan.util.Response;
import cn.hellyuestc.caiyuan.util.Status;

@Controller
public class QuestionController {
	
	@Autowired
	private CommonService commonService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private UserService userService;
	@Autowired
	private TopicService topicService;
	
	/*
	 * 发布问题
	 */
	@RequestMapping(value="/questions", method=RequestMethod.POST)
	@ResponseBody
	public Response addQuestion(long userId, long topicId, String title, String content, HttpServletRequest request) {
		Map<String, String> stringMap = new HashMap<>();
		Map<String, Object> objectMap = new HashMap<>();
		String userName = null;
		String topicName = null;
		
		stringMap = commonService.getUserIdFromRedis(request);
		
		// 未登录
		if (stringMap.get("userId") == null) {
			return new Response(new Status(400, "error"), stringMap);
		}

		long id = Long.parseLong((String) stringMap.get("userId"));
		
		if (id != userId) {
			stringMap.clear();
			stringMap.put("id-error", "请求的id与登陆的用户id不一致");
			return new Response(new Status(400, "error"), stringMap);
		}
		
		userName = userService.getNameById(userId);
		// userId不存在
		if (userName == null) {
			stringMap.put("userId-error", "用户id不存在");
			return new Response(new Status(400, "error"), stringMap);
		}
		
		topicName = topicService.getNameById(topicId);
		// topicId不存在
		if (topicName == null) {
			stringMap.put("topicId-error", "话题id不存在");
			return new Response(new Status(400, "error"), stringMap);
		}
		
		// title长度不合法
		if ((title == null) || (title.equals("")) || (100 < title.length())) {
			stringMap.put("title-error", "请输入1-100个字符的标题");
			return new Response(new Status(400, "error"), stringMap);
		}
		
		// content长度超出范围
		if ((content == null) || (content.equals("")) || (65535 < content.length())) {
			stringMap.put("title-error", "请输入1-65535个字符的内容");
			return new Response(new Status(400, "error"), stringMap);
		}
		
		// 发布问题成功
		Question question = questionService.publishQuestion(userId, userName, topicId, topicName, title, content);
		
		objectMap.put("question", question);
		
		return new Response(new Status(201, "发布问题成功"), objectMap);
	}
	
	/*
	 * 上传问题图片
	 */
	@RequestMapping(value="/questions/{id}/images", method=RequestMethod.POST)
	@ResponseBody
	public Response uploadQuestionImage(@PathVariable long id, MultipartFile questionImage, HttpServletRequest request) {
		Map<String, String> map = new HashMap<>();
		long userId = 0;

		map = commonService.getUserIdFromRedis(request);
		
		// 未登录
		if (map.get("userId") == null) {
			return new Response(new Status(400, "error"), map);
		}
		
		userId = Long.parseLong(map.get("userId"));
		
		if (!MyUtil.isLegalImage(questionImage)) {
			map.clear();
			map.put("image-error", "请上传后缀为.bmp、.jpg、.jpeg、.png、.gif的图片");
			return new Response(new Status(400, "error"), map);
		}

		// 包含原始文件名的字符串
		String originalImageName = questionImage.getOriginalFilename();
		// 提取文件拓展名
		String imageNameExtension = originalImageName.substring(originalImageName.indexOf("."), originalImageName.length());
		String imageName = MyUtil.createRandomCode() + imageNameExtension;
		
		commonService.putImageToLocal(questionImage, request.getServletContext().getRealPath("/questionImages"), imageName);
		ImageUtil.compressQuestionImage(request.getServletContext().getRealPath("/questionImages") + "\\" + imageName, imageName, request.getServletContext().getRealPath("/questionImages/processedImages"));
		
		String imageUrl = "questionImages/" + imageName;
		questionService.addQuestionImage(id, imageUrl);
		
		map.clear();
		map.put("imageUrl", imageUrl);
		map.put("pocessedImageUrl", "questionImages/processedImages/" + imageName);
		return new Response(new Status(201, "上传问题图片成功"), map);
	}
	
	/*
	 * 获取问题（按时间排序）
	 */
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