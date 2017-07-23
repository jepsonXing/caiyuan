package cn.hellyuestc.caiyuan.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface AnswerService {
	
	/*
	 * 增加回答/回答草稿
	 */
	Map<String, Object> addAnswer(long questionId, String content, int isPublish, HttpServletRequest request);

	/*
	 * 更新回答/回答草稿
	 */
	Map<String, Object> updateAnswer(long answerId, String content, HttpServletRequest request);
	
	/*
	 * 发布回答草稿
	 */
	Map<String, Object> publishAnswerDraft(long answerDraftId, HttpServletRequest request);
	
	/*
	 * 获取回答（按时间排序）
	 */
	Map<String, Object> getAnswers(long questionId, String time, int count, String type);
	
	
}
