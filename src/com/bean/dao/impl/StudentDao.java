package com.bean.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bean.dao.StudentInterface;
import com.bean.domain.StudentBean;

@Transactional
public class StudentDao implements StudentInterface {
	@Resource private SessionFactory sessionFactory;
   //添加student信息
	public void addStudent(StudentBean student)
	{   

		sessionFactory.getCurrentSession().save(student);

	}
	//删除student信息
	public void delStudent(int id)
	{
		sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().load(StudentBean.class,id));
	}
	//查询所有学生信息
	@SuppressWarnings("unchecked")
	public List<StudentBean> query()
	{
		return (List<StudentBean>)sessionFactory.getCurrentSession().createQuery("from student").list();
	}
	//按id查询学生信息
	public StudentBean querById(int id)
	{
		return (StudentBean)sessionFactory.getCurrentSession().get(StudentBean.class,id);
	}
	//按学号查询学生信息
	public StudentBean querByStuno(int stuno)
	{
		return (StudentBean)sessionFactory.getCurrentSession().get(StudentBean.class,stuno);
	}
	//按姓名查询学生信息
	@SuppressWarnings("unchecked")
	public List<StudentBean> querByStuname(String stuname)
	{
		return (List<StudentBean>)sessionFactory.getCurrentSession().createSQLQuery( "select * from student where name like ?" ).setParameter(0, stuname).list();

	}
	//获取所有学生记录，分页
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@SuppressWarnings("unchecked")
	public List<StudentBean> findWithPage(int pageSize,int startRow,String hql) throws HibernateException{
		List<StudentBean> recordList=null;
			Query q=sessionFactory.getCurrentSession().createQuery(hql);
			q.setFirstResult(startRow);
			q.setMaxResults(pageSize);
			recordList=q.list();
		return recordList;
	}
	//获取总行数
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public int getRows(String query) throws HibernateException{
		return ((Number)sessionFactory.getCurrentSession().createQuery(query).iterate().next()).intValue();
	}
	//修改学生信息
	public void updateStu(StudentBean student)
	{
		sessionFactory.getCurrentSession().merge(student);
	}
	//验证登陆
	@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
	public StudentBean login(String username,String password) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from StudentBean where name=:name and password=:pwd");
		q.setString("name", username);
		q.setString("pwd", password);
		StudentBean loginuser = (StudentBean)q.uniqueResult();
		if(loginuser != null){
			Hibernate.initialize(loginuser.getTypeid());
		}
		return loginuser;
	}
}
