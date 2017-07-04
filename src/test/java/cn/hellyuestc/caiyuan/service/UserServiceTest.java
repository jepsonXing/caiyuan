package cn.hellyuestc.caiyuan.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-applicationContext.xml", "classpath:mybatis-configuration.xml"})
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void test() {
//		userService.addUserByEmail("1414281762@qq.com", "123456", "123456");
		userService.activateEmail("1414281762@qq.com", "1499134546329e744a670322a4726b638b0e8ca5e8cb3");
	}

}
