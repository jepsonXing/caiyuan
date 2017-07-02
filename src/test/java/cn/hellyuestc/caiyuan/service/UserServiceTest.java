package cn.hellyuestc.caiyuan.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-applicationContext.xml"})
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testAddUser() {
//		System.out.println("---------");
//		System.out.println(userService.addUserByEmail("1414281762@qq.com", "123456", "123456"));
//		System.out.println("---------");
	}

}
