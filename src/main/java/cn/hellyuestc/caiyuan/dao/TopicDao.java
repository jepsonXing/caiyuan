package cn.hellyuestc.caiyuan.dao;

public interface TopicDao {

	String selectNameById(long id);
	
	void updateQuestionCount(long id);
	
}
