package cn.hellyuestc.caiyuan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.hellyuestc.caiyuan.entity.Answer;

public interface AnswerDao {
	
	long insertAnswer(Answer answer);

	List<Answer> listNewAnswerByGmtModified(@Param("questionsId") long questionId, @Param("time") String time, @Param("count") int count);
	
	List<Answer> listNextAnswerByGmtModified(@Param("questionsId") long questionId, @Param("time") String time, @Param("count") int count);
	
}
