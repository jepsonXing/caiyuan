package cn.hellyuestc.caiyuan.service;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.hellyuestc.caiyuan.entity.Question;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-applicationContext.xml", "classpath:mybatis-configuration.xml"})
public class QuestionServiceTest {
	
	@Autowired
	private QuestionService questionService;
	
	@Test
	public void test() {
		Map<String, Object> map = questionService.getQuestions("2017-02-22 11:21:22", 5, "new");
		List<Question> questions = (List<Question>) map.get("questionList");
		System.out.println(questions.size());
	}

}
