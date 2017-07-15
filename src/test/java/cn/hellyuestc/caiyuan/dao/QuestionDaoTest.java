package cn.hellyuestc.caiyuan.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.hellyuestc.caiyuan.entity.Question;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-applicationContext.xml", "classpath:mybatis-configuration.xml"})
public class QuestionDaoTest {

	@Autowired
	private QuestionDao questionDao;
	
	@Test
	public void test() {
		List<Question> questions = questionDao.listNewQuestionByGmtModifiedAndId(1, "2017-02-22 11:21:22", 5);
		System.out.println(questions.size());
	}
	
}
