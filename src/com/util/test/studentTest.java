package com.util.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bean.dao.StudentInterface;
import com.bean.domain.StudentBean;



public class studentTest {
	private static StudentInterface studentDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		try {
			ApplicationContext cxt = new ClassPathXmlApplicationContext("beans.xml");
			studentDao = (StudentInterface)cxt.getBean("studentDao");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void addstudentTest(){
		StudentBean student=new StudentBean();
		student.setTypeid(1);
		student.setName("学生");
		student.setPassword("123456");
		student.setStuno("1001");
		student.setAge(21);
		student.setSex("女");
		student.setTel("123456789");
		student.setAddress("四川文理学院");
		studentDao.addStudent(student);
	}
}
