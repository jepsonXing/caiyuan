package cn.hellyuestc.caiyuan.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Override
	public Map<String, Object> addAnswer(long questionId, String content, HttpServletRequest request) {
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
		answer.setLikeCount(0);
		answer.setUnlikeCount(0);
		Date date = new Date();
		answer.setGmtCreate(MyUtil.formatDate(date, 1));
		answer.setGmtModified(MyUtil.formatDate(date, 1));
		
		long answerId = answerDao.insertAnswer(answer);
		answer.setId(answerId);
		
		map.put("answer", answer);
		return map;
	}

	@Override
	public Map<String, Object> getQuestions(long questionId, String time, int count, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Answer> answerList = new ArrayList<>();
		
		if (type.equals("new")) {
			//获取更新的问题
			answerList = answerDao.listNewAnswerByGmtModified(questionId, time, count);
		} else {
			//获取下一页问题
			answerList = answerDao.listNextAnswerByGmtModified(questionId, time, count);
		}
		
		map.put("count", answerList.size());
		map.put("answerList", answerList);
		return map;
	}

	
	
}
