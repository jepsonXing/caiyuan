package cn.hellyuestc.caiyuan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.hellyuestc.caiyuan.entity.Answer;

public interface AnswerDao {
	
	int selectCountById(long id);
	
	long selectUserIdById(long id);
	
	Answer selectAnswerById(long id);
	
	void insertAnswer(Answer answer);
	
	void updateAnswer(@Param("id") long id, @Param("summary") String summary, @Param("content") String content);

	List<Answer> listNewAnswerByGmtModified(@Param("questionsId") long questionId, @Param("time") String time, @Param("count") int count);
	
	List<Answer> listNextAnswerByGmtModified(@Param("questionsId") long questionId, @Param("time") String time, @Param("count") int count);
	
}
