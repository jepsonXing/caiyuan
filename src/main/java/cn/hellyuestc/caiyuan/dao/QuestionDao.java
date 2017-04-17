package cn.hellyuestc.caiyuan.dao;

import java.util.Date;
import java.util.List;

import cn.hellyuestc.caiyuan.entity.Question;

public interface QuestionDao {

	List<Question> selectGreaterThanLastRefreshTime(Date lastRefreshTime);
	
	List<Question> selectLessThanLastViewTime(Date lastViewTime);
	
}
