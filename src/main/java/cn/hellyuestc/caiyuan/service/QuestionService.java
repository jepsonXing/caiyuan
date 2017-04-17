package cn.hellyuestc.caiyuan.service;

import java.util.List;

import cn.hellyuestc.caiyuan.entity.Question;

public interface QuestionService {

	List<Question> getPrePageNew(String lastRefreshTime);
	
	List<Question> getNextPageNew(String lastViewTime);
	
}
