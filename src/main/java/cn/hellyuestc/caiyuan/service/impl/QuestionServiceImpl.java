package cn.hellyuestc.caiyuan.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hellyuestc.caiyuan.dao.QuestionDao;
import cn.hellyuestc.caiyuan.entity.Question;
import cn.hellyuestc.caiyuan.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionDao questionDao;
	
	/*
	 * 检查时间和请求问题的数量是否合法
	 */
	@Override
	public Map<String, String> validateTimeAndCount(String time, int count) {
		Map<String, String> map = new HashMap<>();
		
		//时间格式不正确
		Pattern p = Pattern.compile(
				"^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$",
				Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
		Matcher m = p.matcher(time.trim());
		if (!m.find()) {
			map.put("time-error", "请输入yyyy-MM-dd HH:MM:SS格式的时间戳");
			return map;
		}
		
		//count不合法
		if ((count < 1) || (20 < count)) {
			map.put("count-error", "count的范围需为1-20");
			return map;
		}
		
		//time和count均合法
		map.put("ok", "time和count均合法");
		return map;
	}
	
	/*
	 * 游客获取问题（按时间）
	 */
	@Override
	public Map<String, Object> getQuestions(String time, int count, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Question> questionList = new ArrayList<>();
		
		if (type.equals("new")) {
			//获取更新的问题
			questionList = questionDao.listNewQuestionByGmtModified(time, count);
		} else {
			//获取下一页问题
			questionList = questionDao.listNextQuestionByGmtModified(time, count);
		}
		
		map.put("count", questionList.size());
		map.put("questionList", questionList);
		return map;
	}
	
	/*
	 * 已登录的用户获取问题（按时间）
	 */
	@Override
	public Map<String, Object> getQuestions(long id, String time, int count, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Question> questionList = new ArrayList<>();
		
		if (type.equals("new")) {
			//获取更新的问题
			questionList = questionDao.listNewQuestionByGmtModifiedAndId(id, time, count);
		} else {
			//获取下一页问题
			questionList = questionDao.listNextQuestionByGmtModifiedAndId(id, time, count);
		}
		map.put("count", questionList.size());
		map.put("questionList", questionList);
		return map;
	}

}
