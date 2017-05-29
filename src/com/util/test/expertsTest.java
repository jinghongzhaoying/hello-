package com.util.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bean.dao.ExpertsInterface;
import com.bean.domain.ExpertsBean;

public class expertsTest {
	private static ExpertsInterface expertsDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		try {
			ApplicationContext cxt = new ClassPathXmlApplicationContext("beans.xml");
			expertsDao = (ExpertsInterface)cxt.getBean("expertsDao");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void addexpertsTest(){
		ExpertsBean experts=new ExpertsBean();
		experts.setTypeid(4);
		experts.setName("×¨¼Ò");
		experts.setPassword("123456");
		experts.setAge(35);
		experts.setSex("ÄÐ");
		experts.setTel("123456789");
		experts.setEmail("zhuanjia@163.com");
		experts.setMajor("ÓïÑÔÑ§");
		expertsDao.addExperts(experts);
	}
}
