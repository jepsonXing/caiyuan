package cn.hellyuestc.caiyuan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.hellyuestc.caiyuan.entity.Question;

public interface QuestionDao {
	
	public List<Question> listNewQuestionByGmtModified(@Param("time") String time, @Param("count") int count);

	public List<Question> listNextQuestionByGmtModified(@Param("time") String time, @Param("count") int count);
	
	public List<Question> listNewQuestionByGmtModifiedAndId(@Param("id") long id, @Param("time") String time, @Param("count") int count);

	public List<Question> listNextQuestionByGmtModifiedAndId(@Param("id") long id, @Param("time") String time, @Param("count") int count);
	
}
