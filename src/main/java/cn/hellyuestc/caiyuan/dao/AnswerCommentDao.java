package cn.hellyuestc.caiyuan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.hellyuestc.caiyuan.entity.AnswerComment;

public interface AnswerCommentDao {
	
	long insertAnswerComment(AnswerComment answerComment);
	
	List<AnswerComment> getAnswerComments(@Param("answerId") long answerId, @Param("time") String time, @Param("count") int count);

}
