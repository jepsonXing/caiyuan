package cn.hellyuestc.caiyuan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hellyuestc.caiyuan.dao.TopicDao;
import cn.hellyuestc.caiyuan.entity.Topic;
import cn.hellyuestc.caiyuan.service.TopicService;

@Service
public class TopicServiceImpl implements TopicService {

	@Autowired
	private TopicDao topicDao;
	
	@Override
	public List<Topic> listNextPage(int pageNumber) {
		return null;
	}

}
