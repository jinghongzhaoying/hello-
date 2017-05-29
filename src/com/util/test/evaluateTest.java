package com.util.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.bean.dao.EvaluationInterface;


public class evaluateTest {
	private static EvaluationInterface evaluateDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		try {
			ApplicationContext cxt = new ClassPathXmlApplicationContext("beans.xml");
			evaluateDao = (EvaluationInterface)cxt.getBean("evauateDao");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void getRowsByTeacheridTest(){
		int rows=evaluateDao.getRowsByTeacherid("select count(*) from EvaluationBean where teacherid=1");
		System.out.println(rows);
	}
}
