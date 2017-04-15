package cn.hellyuestc.caiyuan.service;

import java.util.Date;
import java.util.List;

import cn.hellyuestc.caiyuan.entity.Question;

public interface QuestionService {

	List<Question> getPrePageNew(Date lastRefreshTime);
	
}
