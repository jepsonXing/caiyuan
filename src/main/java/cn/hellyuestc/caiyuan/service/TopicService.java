package cn.hellyuestc.caiyuan.service;

import java.util.List;

import cn.hellyuestc.caiyuan.entity.Topic;

public interface TopicService {
	
	List<Topic> listNextPage(int pageNumber);

}
