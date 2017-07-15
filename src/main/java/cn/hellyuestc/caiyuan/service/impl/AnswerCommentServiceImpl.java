package cn.hellyuestc.caiyuan.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hellyuestc.caiyuan.dao.AnswerCommentDao;
import cn.hellyuestc.caiyuan.dao.AnswerDao;
import cn.hellyuestc.caiyuan.dao.UserDao;
import cn.hellyuestc.caiyuan.entity.AnswerComment;
import cn.hellyuestc.caiyuan.service.AnswerCommentService;
import cn.hellyuestc.caiyuan.util.MyUtil;

@Service
public class AnswerCommentServiceImpl implements AnswerCommentService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private AnswerDao answerDao;
	@Autowired
	private AnswerCommentDao answerCommentDao;

	@Override
	public Map<String, Object> addAnswerComment(long answerId, long fromUserId, long toUserId, String content) {
		Map<String, Object> map = new HashMap<>();
		AnswerComment answerComment = new AnswerComment();
		
		if (answerDao.selectCountById(answerId) == 0) {
			map.put("answerId-error", "不存在此问题id");
			return map;
		}
		answerComment.setAnswerId(answerId);
		
		String fromUserName = userDao.selectNameById(fromUserId);
		if (fromUserName == null) {
			map.put("fromUserId-error", "不存在此用户");
			return map;
		}
		answerComment.setFromUserId(fromUserId);
		answerComment.setFromUserName(fromUserName);
		
		if (toUserId != 0) {
			String toUserName = userDao.selectNameById(toUserId);
			if (toUserName == null) {
				map.put("toUserId-error", "不存在此用户");
				return map;
			}
			answerComment.setToUserId(fromUserId);
			answerComment.setToUserName(fromUserName);
		} else {
			answerComment.setToUserId(0);
			answerComment.setToUserName(null);
		}
		
		if ((content == null) || (content.equals(""))) {
			map.put("content-error", "评论内容不能为空");
			return map;
		}
		answerComment.setContent(content);
		
		Date date = new Date();
		answerComment.setGmtCreate(MyUtil.formatDate(date, 1));
		answerComment.setGmtModified(MyUtil.formatDate(date, 1));
		
		long id = answerCommentDao.insertAnswerComment(answerComment);
		answerComment.setId(id);
		
		map.put("answerComment", answerComment);
		return map;
	}

	@Override
	public Map<String, Object> getAnswerComments(long answerId, String time, int count) {
		Map<String, Object> map = new HashMap<>();
		List<AnswerComment> answerComments = null;

		if (answerDao.selectCountById(answerId) == 0) {
			map.put("answerId-error", "不存在此问题id");
			return map;
		}
		
		answerComments = answerCommentDao.getAnswerComments(answerId, time, count);
		
		map.put("count", answerComments.size());
		map.put("answerComments", answerComments);
		return map;
	}
	
	

}
