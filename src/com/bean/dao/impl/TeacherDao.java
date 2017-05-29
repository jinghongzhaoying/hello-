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
import com.bean.dao.TeacherInterface;
import com.bean.domain.TeacherBean;

@Transactional
public class TeacherDao implements TeacherInterface{
	@Resource private SessionFactory sessionFactory;
   //添加teacher信息
	public void addTeacher(TeacherBean teacher)
	{   
		sessionFactory.getCurrentSession().save(teacher);
	}
	//删除teacher信息
	public void delTeacher(int id)
	{
		sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().load(TeacherBean.class,id));
	}
	//查询所有老师信息
	@SuppressWarnings("unchecked")
	public List<TeacherBean> query()
	{
		return (List<TeacherBean>)sessionFactory.getCurrentSession().createQuery("from TeacherBean").list();
	}
	//查询分数最高的前五条记录
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@SuppressWarnings("unchecked")
	public List<TeacherBean> queryIndex(){
		String hql="from TeacherBean as t order by score desc";
		Query q=sessionFactory.getCurrentSession().createQuery(hql).setFirstResult(0).setMaxResults(5);
		List<TeacherBean> list=q.list();
		return list;
	}
	//获取所有销售记录，分页
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@SuppressWarnings("unchecked")
	public List<TeacherBean> findWithPage(int pageSize,int startRow,String hql) throws HibernateException{
		List<TeacherBean> recordList=null;
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
	
	//按id查询老师信息
	public TeacherBean querById(int id)
	{
		return (TeacherBean)sessionFactory.getCurrentSession().get(TeacherBean.class,id);
	}
	
	//修改老师信息
	public void updateTea(TeacherBean teacher)
	{
		sessionFactory.getCurrentSession().merge(teacher);
	}
	//验证登陆
	@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
	public TeacherBean login(String username,String password) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from TeacherBean where name=:name and password=:pwd");
		q.setString("name", username);
		q.setString("pwd", password);
		TeacherBean loginuser = (TeacherBean)q.uniqueResult();
		if(loginuser != null){
			Hibernate.initialize(loginuser.getTypeid());
		}
		return loginuser;
	}
}
