package cn.hellyuestc.caiyuan.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-applicationContext.xml", "classpath:mybatis-configuration.xml"})
public class UserDaoTest {

	@Autowired
	private UserDao userDao;
	
	@Test
	public void testSelectEmailCount() {
		System.out.println("------------");
		System.out.println(userDao.selectEmailCount("hellyuestc@gmail.com"));
		System.out.println("------------");
	}
	
}
