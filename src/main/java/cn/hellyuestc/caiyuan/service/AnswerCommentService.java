package cn.hellyuestc.caiyuan.service;

import java.util.Map;

public interface AnswerCommentService {
	
	/*
	 * 增加问题评论
	 */
	Map<String, Object> addAnswerComment(long answerId, long fromUserId, long toUserId, String content);

	/*
	 * 获取评论
	 */
	Map<String, Object> getAnswerComments(long answerId, String time, int count);
	
}
