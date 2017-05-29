package com.bean.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bean.domain.StudentBean;


public interface StudentInterface {

	public abstract void addStudent(StudentBean student);

	//删除student信息

	public abstract void delStudent(int id);

	//查询所有学生信息
	public abstract List<StudentBean> query();

	//按id查询学生信息

	public abstract StudentBean querById(int id);

	//按学号查询学生信息
	public abstract StudentBean querByStuno(int stuno);

	//按姓名查询学生信息
	public abstract List<StudentBean> querByStuname(String stuname);
	
	//获取所有销售记录，分页
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<StudentBean> findWithPage(int pageSize,int startRow,String hql) throws HibernateException;
	
	//获取总行数
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public int getRows(String query) throws HibernateException;
    //修改学生信息
	public void updateStu(StudentBean student);
	//验证登陆
	@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
	public StudentBean login(String username,String password) ;

}