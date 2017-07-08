package cn.hellyuestc.caiyuan.service;

import java.util.Map;

public interface QuestionService {
	
	/*
	 * 检查时间和请求问题的数量是否合法
	 */
	public Map<String, String> validateTimeAndCount(String time, int count);

	/*
	 * 游客获取问题（按时间）
	 */
	public Map<String, Object> getQuestions(String time, int count, String type);
	
	/*
	 * 已登录的用户获取问题（按时间）
	 */
	public Map<String, Object> getQuestions(long id, String time, int count, String type);
	
}
