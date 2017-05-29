package com.util.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bean.dao.AdminInterface;
import com.bean.domain.AdminBean;


public class adminTest {
	private static AdminInterface adminDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		try {
			ApplicationContext cxt = new ClassPathXmlApplicationContext("beans.xml");
			adminDao = (AdminInterface)cxt.getBean("adminDao");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void addadminTest(){
		AdminBean admin=new AdminBean();
		admin.setTypeid(6);
		admin.setName("π‹¿Ì‘±");
		admin.setPassword("123456");
		adminDao.addAdmin(admin);
	}
}
