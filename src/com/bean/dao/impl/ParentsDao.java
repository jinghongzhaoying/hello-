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


import com.bean.dao.ParentsInterface;
import com.bean.domain.ParentsBean;
@Transactional
public class ParentsDao implements ParentsInterface{
	@Resource private SessionFactory sessionFactory;
   //���Parents��Ϣ
 
	public void addParents(ParentsBean parents)
	{   

		sessionFactory.getCurrentSession().save(parents);

	}
	//ɾ��parents��Ϣ
  
	public void delParents(int id)
	{
		sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().load(ParentsBean.class,id));
	}
	//��ѯ����parents��Ϣ

	@SuppressWarnings("unchecked")
	public List<ParentsBean> query()
	{
		return (List<ParentsBean>)sessionFactory.getCurrentSession().createQuery("from parents").list();
	}
	//��id��ѯparents��Ϣ
	public ParentsBean querById(int id)
	{
		return (ParentsBean)sessionFactory.getCurrentSession().get(ParentsBean.class,id);
	}
	//��������ѯparents��Ϣ
	@SuppressWarnings("unchecked")
	public List<ParentsBean> querByParname(String parname)
	{
		return (List<ParentsBean>)sessionFactory.getCurrentSession().createSQLQuery( "select * from parents where name like ?" ).setParameter(0, parname).list();

	}
	//��ȡ���мҳ���¼����ҳ
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@SuppressWarnings("unchecked")
	public List<ParentsBean> findWithPage(int pageSize,int startRow,String hql) throws HibernateException{
		List<ParentsBean> recordList=null;
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
	//�޸�parents��Ϣ
	public void updatePar(ParentsBean parents)
	{
		sessionFactory.getCurrentSession().merge(parents);
	}
	//��֤��½
	@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
	public ParentsBean login(String username,String password) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from ParentsBean where name=:name and password=:pwd");
		q.setString("name", username);
		q.setString("pwd", password);
		ParentsBean loginuser = (ParentsBean)q.uniqueResult();
		if(loginuser != null){
			Hibernate.initialize(loginuser.getTypeid());
		}
		return loginuser;
	}
}
