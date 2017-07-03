package cn.hellyuestc.caiyuan.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.JedisPool;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-applicationContext.xml", "classpath:mybatis-configuration.xml"})
public class SessionServiceTest {

	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private JedisPool jedisPool;
	
	@Test
	public void test() {
		System.out.println(sessionService.login("18483661669", "123456", null));
	}
	
}
