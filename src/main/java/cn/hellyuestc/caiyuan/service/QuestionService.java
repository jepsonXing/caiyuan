package cn.hellyuestc.caiyuan.service;

import java.util.Map;

import cn.hellyuestc.caiyuan.entity.Question;

public interface QuestionService {
	
	/*
	 * 发布问题
	 */
	Question publishQuestion(long userId, String userName, long topicId, String topicName, String title, String content);
	
	/*
	 * 增加问题图片
	 */
	public void addQuestionImage(long questionId, String imageUrl);
	
	/*
	 * 检查时间和请求问题的数量是否合法
	 */
	Map<String, String> validateTimeAndCount(String time, int count);

	/*
	 * 游客获取问题（按时间）
	 */
	Map<String, Object> getQuestions(String time, int count, String type);
	
	/*
	 * 已登录的用户获取问题（按时间）
	 */
	Map<String, Object> getQuestions(long id, String time, int count, String type);
	
}
