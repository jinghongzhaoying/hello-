package com.bean.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bean.domain.TeacherBean;


public interface TeacherInterface {

	public abstract void addTeacher(TeacherBean teacher);

	//删除teacher信息

	public abstract void delTeacher(int id);

	//查询所有老师信息
	public abstract List<TeacherBean> query();
	
	//查询分数最高的前五条记录
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<TeacherBean> queryIndex();
	
	//获取所有销售记录，分页
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<TeacherBean> findWithPage(int pageSize,int startRow,String hql) throws HibernateException;
	
	//获取总行数
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public int getRows(String query) throws HibernateException;

	//按id查询老师信息
	public abstract TeacherBean querById(int id);
	
     //修改老师信息
	public void updateTea(TeacherBean teacher);
	
	//验证登陆
	@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
	public TeacherBean login(String username,String password);

}