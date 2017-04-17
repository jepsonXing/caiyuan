package cn.hellyuestc.caiyuan.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.chainsaw.Main;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;

import cn.hellyuestc.caiyuan.entity.Question;
import cn.hellyuestc.caiyuan.entity.Topic;

public class PagehelperTest {
	
	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-applicationContext.xml");
		TopicDao topicDao = context.getBean(TopicDao.class);
		PageHelper.startPage(2, 5);
		List<Topic> topicList = topicDao.selectAll();
		for (Topic topic : topicList) {
			System.out.println(topic.getName());
		}
	}
	
	@Test
	public void testQuestionDao() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-applicationContext.xml");
		QuestionDao questionDao = context.getBean(QuestionDao.class);
		Date lastRefreshTime = new Date(2017, 3, 1, 1, 1, 1);
//		List<Question> questionList = questionDao.selectAllOrderGmtModifiedTime(lastRefreshTime);
//		System.out.println(questionList);
	}
	
}
