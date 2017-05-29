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

import com.bean.dao.LeaderInterface;
import com.bean.domain.LeaderBean;

@Transactional
public class LeaderDao implements LeaderInterface{
	@Resource private SessionFactory sessionFactory;
   //添加leader信息
	public void addLeader(LeaderBean leader)
	{   

		sessionFactory.getCurrentSession().save(leader);

	}
	//删除leader信息
	public void delLeader(int id)
	{
		sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().load(LeaderBean.class,id));
	}
	//查询所有leader信息
	@SuppressWarnings("unchecked")
	public List<LeaderBean> query()
	{
		return (List<LeaderBean>)sessionFactory.getCurrentSession().createQuery("from leader").list();
	}
	//按id查询leader信息
	public LeaderBean querById(int id)
	{
		return (LeaderBean)sessionFactory.getCurrentSession().get(LeaderBean.class,id);
	}
	//按姓名查询leader信息
	@SuppressWarnings("unchecked")
	public List<LeaderBean> querBylename(String lename)
	{
		return (List<LeaderBean>)sessionFactory.getCurrentSession().createSQLQuery( "select * from leader where name like ?" ).setParameter(0, lename).list();

	}
	//获取所有领导记录，分页
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@SuppressWarnings("unchecked")
	public List<LeaderBean> findWithPage(int pageSize,int startRow,String hql) throws HibernateException{
		List<LeaderBean> recordList=null;
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
	//修改leader信息
	public void updateLe(LeaderBean leader)
	{
		sessionFactory.getCurrentSession().merge(leader);
	}
	//验证登陆
	@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
	public LeaderBean login(String username,String password) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from LeaderBean where name=:name and password=:pwd");
		q.setString("name", username);
		q.setString("pwd", password);
		LeaderBean loginuser = (LeaderBean)q.uniqueResult();
		if(loginuser != null){
			Hibernate.initialize(loginuser.getTypeid());
		}
		return loginuser;
	}
}
