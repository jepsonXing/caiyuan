package cn.hellyuestc.caiyuan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hellyuestc.caiyuan.dao.QuestionDao;
import cn.hellyuestc.caiyuan.entity.Question;
import cn.hellyuestc.caiyuan.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	QuestionDao questionDao;

	@Override
	public List<Question> getPrePageNew(String lastRefreshTimeStr) {
		return null;
	}

	@Override
	public List<Question> getNextPageNew(String lastViewTimeStr) {
		return null;
	}

}
