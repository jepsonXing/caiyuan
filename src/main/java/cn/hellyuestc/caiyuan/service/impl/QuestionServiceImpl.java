package cn.hellyuestc.caiyuan.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.hellyuestc.caiyuan.dao.QuestionDao;
import cn.hellyuestc.caiyuan.entity.Page;
import cn.hellyuestc.caiyuan.entity.Question;
import cn.hellyuestc.caiyuan.service.QuestionService;
import cn.hellyuestc.caiyuan.util.FormatDateUtil;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	QuestionDao questionDao;

	@Override
	public List<Question> getPrePageNew(String lastRefreshTimeStr) {
		try {
			Date lastRefreshTime = FormatDateUtil.FormatStringToDate(lastRefreshTimeStr);
			return questionDao.selectGreaterThanLastRefreshTime(lastRefreshTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Question> getNextPageNew(String lastViewTimeStr) {
		try {
			Date lastViewTime = FormatDateUtil.FormatStringToDate(lastViewTimeStr);
			return questionDao.selectLessThanLastViewTime(lastViewTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("跳过了");
		return null;
	}

}
