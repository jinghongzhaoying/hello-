package com.util.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bean.dao.LeaderInterface;
import com.bean.domain.LeaderBean;




public class leaderTest {
	private static LeaderInterface leaderDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		try {
			ApplicationContext cxt = new ClassPathXmlApplicationContext("beans.xml");
			leaderDao = (LeaderInterface)cxt.getBean("leaderDao");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void addleaderTest(){
		LeaderBean leader=new LeaderBean();
		leader.setTypeid(3);
		leader.setName("领导");
		leader.setPassword("123456");
		leader.setAge(34);
		leader.setSex("男");
		leader.setTel("123456789");
		leader.setEmail("leader@163.com");
		leader.setPosition("书记");
		leaderDao.addLeader(leader);
	}
}
