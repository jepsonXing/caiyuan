package cn.hellyuestc.caiyuan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.hellyuestc.caiyuan.entity.Question;

public interface QuestionDao {
	
	int selectCountById(long id);
	
	long insertQuestion(Question question);
	
	void insertQuestionImageUrl(@Param("questionId") long questionId,@Param("imageUrl")  String imageUrl);
	
	List<Question> listNewQuestionByGmtModified(@Param("time") String time, @Param("count") int count);

	List<Question> listNextQuestionByGmtModified(@Param("time") String time, @Param("count") int count);
	
	List<Question> listNewQuestionByGmtModifiedAndId(@Param("id") long id, @Param("time") String time, @Param("count") int count);

	List<Question> listNextQuestionByGmtModifiedAndId(@Param("id") long id, @Param("time") String time, @Param("count") int count);
	
}
