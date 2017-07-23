package cn.hellyuestc.caiyuan.service;

import java.util.Map;

import cn.hellyuestc.caiyuan.entity.Question;

public interface QuestionService {
	
	/*
	 * 增加问题
	 */
	Map<String, Object> addQuestion(long userId, long topicId, String title, String content, byte isPublish);
	
	/*
	 * 增加问题图片
	 */
	void addQuestionImage(long questionId, String imageUrl);
	
	/*
	 * 更新问题
	 */
	Map<String, Object> updateQuestion(long userId, long questionId, String title, String content);
	
	/*
	 * 将草稿发布
	 */
	Map<String, Object> publishDraft(long questionId, long userId);
	
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
