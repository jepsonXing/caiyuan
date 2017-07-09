package cn.hellyuestc.caiyuan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hellyuestc.caiyuan.dao.TopicDao;
import cn.hellyuestc.caiyuan.service.TopicService;

@Service
public class TopicServiceImpl implements TopicService {
	
	@Autowired
	private TopicDao topicDao;

	/*
	 * 根据id获取话题名称
	 */
	@Override
	public String getNameById(long id) {
		return topicDao.selectNameById(id);
	}
	
}
