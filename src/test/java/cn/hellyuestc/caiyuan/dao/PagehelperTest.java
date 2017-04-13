package cn.hellyuestc.caiyuan.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;

import cn.hellyuestc.caiyuan.entity.Topic;

public class PagehelperTest {
	
	@org.junit.Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-applicationContext.xml");
		TopicDao topicDao = context.getBean(TopicDao.class);
		PageHelper.startPage(2, 5);
		List<Topic> topicList = topicDao.listSelectAll();
		for (Topic topic : topicList) {
			System.out.println(topic.getName());
		}
	}

}
