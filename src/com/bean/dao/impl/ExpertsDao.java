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

import com.bean.dao.ExpertsInterface;
import com.bean.domain.ExpertsBean;

@Transactional
public class ExpertsDao implements ExpertsInterface{
	@Resource private SessionFactory sessionFactory;
   //添加experts信息
	public void addExperts(ExpertsBean experts)
	{   

		sessionFactory.getCurrentSession().save(experts);

	}
	//删除experts信息
 
	public void delExperts(int id)
	{
		sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().load(ExpertsBean.class,id));
	}
	//查询所有experts信息

	@SuppressWarnings("unchecked")
	public List<ExpertsBean> query()
	{
		return (List<ExpertsBean>)sessionFactory.getCurrentSession().createQuery("from experts").list();
	}
	//按id查询experts信息
  
	public ExpertsBean querById(int id)
	{
		return (ExpertsBean)sessionFactory.getCurrentSession().get(ExpertsBean.class,id);
	}
	//按姓名查询experts信息
	
	@SuppressWarnings("unchecked")
	public List<ExpertsBean> querByExname(String exname)
	{
		return (List<ExpertsBean>)sessionFactory.getCurrentSession().createSQLQuery( "select * from experts where name like ?" ).setParameter(0, exname).list();

	}
	//获取所有领导记录，分页
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@SuppressWarnings("unchecked")
	public List<ExpertsBean> findWithPage(int pageSize,int startRow,String hql) throws HibernateException{
		List<ExpertsBean> recordList=null;
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
	//修改experts信息
	
	public void updateEx(ExpertsBean experts)
	{
		sessionFactory.getCurrentSession().merge(experts);
	}

	//验证登陆
	@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
	public ExpertsBean login(String username,String password) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from ExpertsBean where name=:name and password=:pwd");
		q.setString("name", username);
		q.setString("pwd", password);
		ExpertsBean loginuser = (ExpertsBean)q.uniqueResult();
		if(loginuser != null){
			Hibernate.initialize(loginuser.getTypeid());
		}
		return loginuser;
	}
}
