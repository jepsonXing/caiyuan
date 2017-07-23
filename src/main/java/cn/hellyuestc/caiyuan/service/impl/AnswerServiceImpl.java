package cn.hellyuestc.caiyuan.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.hellyuestc.caiyuan.dao.AnswerDao;
import cn.hellyuestc.caiyuan.dao.QuestionDao;
import cn.hellyuestc.caiyuan.dao.UserDao;
import cn.hellyuestc.caiyuan.entity.Answer;
import cn.hellyuestc.caiyuan.service.AnswerService;
import cn.hellyuestc.caiyuan.service.CommonService;
import cn.hellyuestc.caiyuan.util.MyUtil;

@Service
public class AnswerServiceImpl implements AnswerService {
	
	@Autowired
	private CommonService commonService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private QuestionDao questionDao;
	@Autowired
	private AnswerDao answerDao;

	@Transactional
	@Override
	public Map<String, Object> addAnswer(long questionId, String content, int isPublish, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		Answer answer = null;
		long userId = 0;
		
		if ((content == null) || (content.equals(""))) {
			map.put("content-error", "回答内容不能为空");
		}
		answer.setContent(content);
		if (content.length() > 100) {
			answer.setSummary(content.substring(0, 99));
		} else {
			answer.setSummary(content);
		}
		
		if (questionDao.selectCountById(questionId) == 0) {
			map.put("questionId-error", "不存在此问题id");
			return map;
		}
		answer.setQuestionId(questionId);
		
		map = commonService.getUserIdFromRedis(request);
		if (map.get("userId") == null) {
			return map;
		}
		
		userId = (long) map.get("userId");
		answer.setUserId(userId);
		answer.setUserName(userDao.selectNameById(userId));
		answer.setIsPublish(isPublish);
		answer.setLikeCount(0);
		answer.setUnlikeCount(0);
		Date date = new Date();
		answer.setGmtCreate(MyUtil.formatDate(date, 1));
		answer.setGmtModified(MyUtil.formatDate(date, 1));
		
		if (isPublish == 1) {
			questionDao.updataAnswerCount(questionId);
		}
		answerDao.insertAnswer(answer);
		
		map.put("answer", answer);
		return map;
	}
	
	@Override
	public Map<String, Object> updateAnswer(long answerId, String content, HttpServletRequest request) {
		Map<String, Object> map = null;
		
		map = commonService.getUserIdFromRedis(request);
		if (map.get("userId") == null) {
			return map;
		}
		long userId = (long) map.get("userId");
		map.clear();
		
		long ownerId = answerDao.selectUserIdById(answerId);
		if (userId != ownerId) {
			map.put("error", "没有更新此问题的权限");
			return map;
		}
		
		// content长度超出范围
		if ((content == null) || (content.equals("")) || (65535 < content.length())) {
			map.put("error", "请输入1-65535个字符的内容");
			return map;
		}
		
		answerDao.updateAnswer(answerId, content.substring(0, 99), content);
		
		map.put("answer", answerDao.selectAnswerById(answerId));
		return map;
	}
	
	/*
	 * 发布回答草稿
	 */
	@Transactional
	@Override
	public Map<String, Object> publishAnswerDraft(long answerDraftId, HttpServletRequest request) {
		Map<String, Object> map = null;
		
		map = commonService.getUserIdFromRedis(request);
		if (map.get("userId") == null) {
			return map;
		}
		long userId = (long) map.get("userId");
		map.clear();
		
		long ownerId = answerDao.selectUserIdById(answerDraftId);
		if (userId != ownerId) {
			map.put("error", "没有发布此问题草稿的权限");
			return map;
		}
		
//		questionDao.updataAnswerCount(id);
		
		
		
		
		
		
		
	}

	@Transactional
	@Override
	public Map<String, Object> getAnswers(long questionId, String time, int count, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Answer> answerList = new ArrayList<>();
		
		questionDao.updataScanCount(questionId);
		
		if (type.equals("new")) {
			//获取更新的回答
			answerList = answerDao.listNewAnswerByGmtModified(questionId, time, count);
		} else {
			//获取下一页回答
			answerList = answerDao.listNextAnswerByGmtModified(questionId, time, count);
		}
		
		map.put("count", answerList.size());
		map.put("answerList", answerList);
		return map;
	}

	
	
}
