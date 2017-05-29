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
   //���leader��Ϣ
	public void addLeader(LeaderBean leader)
	{   

		sessionFactory.getCurrentSession().save(leader);

	}
	//ɾ��leader��Ϣ
	public void delLeader(int id)
	{
		sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().load(LeaderBean.class,id));
	}
	//��ѯ����leader��Ϣ
	@SuppressWarnings("unchecked")
	public List<LeaderBean> query()
	{
		return (List<LeaderBean>)sessionFactory.getCurrentSession().createQuery("from leader").list();
	}
	//��id��ѯleader��Ϣ
	public LeaderBean querById(int id)
	{
		return (LeaderBean)sessionFactory.getCurrentSession().get(LeaderBean.class,id);
	}
	//��������ѯleader��Ϣ
	@SuppressWarnings("unchecked")
	public List<LeaderBean> querBylename(String lename)
	{
		return (List<LeaderBean>)sessionFactory.getCurrentSession().createSQLQuery( "select * from leader where name like ?" ).setParameter(0, lename).list();

	}
	//��ȡ�����쵼��¼����ҳ
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
	//��ȡ������
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public int getRows(String query) throws HibernateException{
		return ((Number)sessionFactory.getCurrentSession().createQuery(query).iterate().next()).intValue();
	}
	//�޸�leader��Ϣ
	public void updateLe(LeaderBean leader)
	{
		sessionFactory.getCurrentSession().merge(leader);
	}
	//��֤��½
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
