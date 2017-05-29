package com.util.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bean.dao.TeacherInterface;
import com.bean.domain.TeacherBean;


public class teacherTest {
	private static TeacherInterface teacherDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		try {
			ApplicationContext cxt = new ClassPathXmlApplicationContext("beans.xml");
			teacherDao = (TeacherInterface)cxt.getBean("teacherDao");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void addteacherTest(){
		TeacherBean teacher=new TeacherBean();
		teacher.setTypeid(2);
		teacher.setName("王老师");
		teacher.setPassword("123456");
		teacher.setAge(22);
		teacher.setSex("女");
		teacher.setTel("1234567890");
		teacher.setEmail("jingblue25@yahoo.cn");
		teacher.setCourse("大学语文");
		teacher.setDegree("博士");
		teacher.setScore(88f);
		teacher.setEvalsum(0);
		teacherDao.addTeacher(teacher);
	}
	@Test
	public void updateTeaTest(){
		TeacherBean teacher=teacherDao.querById(1);
		System.out.println("qian:"+teacher.getScore());
		teacher.setScore(0f);
		teacherDao.updateTea(teacher);
		System.out.println("hou:"+teacher.getScore());
	}
}
