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
import org.springframework.web.multipart.MultipartFile;

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
	
	/*
	 * 发布问题
	 */
	@RequestMapping(value="/questions", method=RequestMethod.POST)
	@ResponseBody
	public Response PublishQuestion(long topicId, String title, String content, HttpServletRequest request) {
		Map<String, Object> map = null;
		
		map = commonService.getUserIdFromRedis(request);
		
		// 未登录
		if (map.get("userId") == null) {
			return new Response(new Status(400, "error"), map);
		}

		long userId = (long) map.get("userId");
		
		map = questionService.addQuestion(userId, topicId, title, content, (byte) 1);
		if (map.get("question") == null) {
			return new Response(new Status(400, "error"), map);
		} else {
			return new Response(new Status(201, "发布问题成功"), map);
		}
	}
	
	/*
	 * 保存问题草稿
	 */
	@RequestMapping(value="/questionDrafts", method=RequestMethod.POST)
	@ResponseBody
	public Response saveQuestionToDraft(long topicId, String title, String content, HttpServletRequest request) {
		Map<String, Object> map = null;
		
		map = commonService.getUserIdFromRedis(request);
		
		// 未登录
		if (map.get("userId") == null) {
			return new Response(new Status(400, "error"), map);
		}

		long userId = (long) map.get("userId");
		
		map = questionService.addQuestion(userId, topicId, title, content, (byte) 0);
		if (map.get("question") == null) {
			return new Response(new Status(400, "error"), map);
		} else {
			return new Response(new Status(201, "保存问题草稿成功"), map);
		}
	}
	
	/*
	 * 更新问题
	 */
	@RequestMapping(value="/questions/{id}", method=RequestMethod.POST)
	@ResponseBody
	public Response updateQuestion(@PathVariable("id") long questionId, String title, String content, HttpServletRequest request) {
		Map<String, Object> map = null;
		
		map = commonService.getUserIdFromRedis(request);
		
		// 未登录
		if (map.get("userId") == null) {
			return new Response(new Status(400, "error"), map);
		}

		long userId = (long) map.get("userId");
		
		map = questionService.updateQuestion(userId, questionId, title, content);
		if (map.get("question") == null) {
			return new Response(new Status(400, "error"), map);
		} else {
			return new Response(new Status(201, "更新问题成功"), map);
		}
	}
	
	/*
	 * 更新问题草稿
	 */
	@RequestMapping(value="/questionDrafts/{id}", method=RequestMethod.POST)
	@ResponseBody
	public Response updateDraft(@PathVariable("id") long questionId, String title, String content, HttpServletRequest request) {
		return updateQuestion(questionId, title, content, request);
	}
	
	/*
	 * 将草稿发布
	 */
	@RequestMapping(value="/questionDrafts/{id}", params={"field=isPublish"}, method=RequestMethod.GET)
	@ResponseBody
	public Response publishDraft(@PathVariable("id") long questionId, HttpServletRequest request) {
		Map<String, Object> map = null;
		
		map = commonService.getUserIdFromRedis(request);
		
		// 未登录
		if (map.get("userId") == null) {
			return new Response(new Status(400, "error"), map);
		}

		long userId = (long) map.get("userId");
		
		map = questionService.publishDraft(questionId, userId);
		if (map.get("question") == null) {
			return new Response(new Status(400, "error"), map);
		} else {
			return new Response(new Status(201, "发布问题成功"), map);
		}
	}
	
	/*
	 * 上传问题图片
	 */
	@RequestMapping(value="/questions/{id}/images", method=RequestMethod.POST)
	@ResponseBody
	public Response uploadQuestionImage(@PathVariable long id, MultipartFile questionImage, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		long userId = 0;

		map = commonService.getUserIdFromRedis(request);
		
		// 未登录
		if (map.get("userId") == null) {
			return new Response(new Status(400, "error"), map);
		}
		
		userId = (long) map.get("userId");
		
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
	@RequestMapping(value="/questions", params={"offset", "limit", "type=new"}, method=RequestMethod.GET)
	@ResponseBody
	public Response getNewQuestionsByTime(@RequestParam("offset") String time, @RequestParam("limit") int count, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
 		long userId = 0;
 		
 		//检查时间和请求的问题数量是否合法
 		map = commonService.validateTimeAndCount(time, count);
 		if (map.get("ok") == null) {
 			return new Response(new Status(400, "error"), map);
 		}

 		map = commonService.getUserIdFromRedis(request);
 		
		// 未登录
		if (map.get("userId") == null) {
			// 游客，返回所有话题中的问题
			map = questionService.getQuestions(time, count, "new");
			return new Response(new Status(200, "ok"), map);
		}

		userId = Long.parseLong((String) map.get("userId"));
		
		map = questionService.getQuestions(userId, time, count, "new");
		
		return new Response(new Status(200, "ok"), map);
	}
	
//	@RequestMapping(value="/questions", params={"offset={time}", "limit={count}", "type=next"}, method=RequestMethod.GET)
//	@ResponseBody
//	public Response getNextQuestionsByTime(@PathVariable String time, @PathVariable int count, HttpServletRequest request) {
//		Map<String, String> stringMap = new HashMap<>();
//		Map<String, Object> objectMap = new HashMap<>();
// 		long userId = 0;
// 		
// 		//检查时间和请求的问题数量是否合法
// 		stringMap = questionService.validateTimeAndCount(time, count);
// 		if (stringMap.get("ok") == null) {
// 			return new Response(new Status(400, "error"), stringMap);
// 		}
//
// 		stringMap = commonService.getUserIdFromRedis(request);
//		
//		// 未登录
//		if (stringMap.get("userId") == null) {
//			// 游客，返回所有话题中的问题
//			objectMap = questionService.getQuestions(time, count, "next");
//		}
//
//		userId = Long.parseLong((String) stringMap.get("userId"));
//		
//		objectMap = questionService.getQuestions(userId, time, count, "next");
//		
//		return new Response(new Status(200, "ok"), objectMap);
//	}

}