package com.util.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bean.dao.ParentsInterface;
import com.bean.domain.ParentsBean;

public class parentsTest {
	private static ParentsInterface parentsDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		try {
			ApplicationContext cxt = new ClassPathXmlApplicationContext("beans.xml");
			parentsDao = (ParentsInterface)cxt.getBean("parentsDao");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void addparentsTest(){
		ParentsBean parents=new ParentsBean();
		parents.setTypeid(5);
		parents.setName("¼Ò³¤");
		parents.setPassword("123456");
		parents.setTel("123456789");
		parentsDao.addParents(parents);
	}
}
