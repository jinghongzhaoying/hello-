package com.bean.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bean.dao.EvaluationInterface;
import com.bean.domain.EvaluationBean;
import com.bean.domain.StudentBean;
@Transactional
public class EvaluationDao implements EvaluationInterface{
	@Resource private SessionFactory sessionFactory;
   //���Evaluation
	public void addEvaluation(EvaluationBean eval)
	{   
		sessionFactory.getCurrentSession().save(eval);
	}
	//ɾ��Evaluation
	public void delEvaluation(int id)
	{
		sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().load(EvaluationBean.class,id));
	}
	//��ѯEvaluation
	@SuppressWarnings("unchecked")
	public List<EvaluationBean> query()
	{
		return (List<EvaluationBean>)sessionFactory.getCurrentSession().createQuery("from EvaluationBean").list();
	}
	//��id��ѯEvaluation
	public EvaluationBean queryById(int id)
	{
		return (EvaluationBean)sessionFactory.getCurrentSession().get(EvaluationBean.class,id);
	}
	//��ȡ����ѧ����¼����ҳ
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@SuppressWarnings("unchecked")
	public List<EvaluationBean> findWithPage(int pageSize,int startRow) throws HibernateException{
		List<EvaluationBean> recordList=null;
			Query q=sessionFactory.getCurrentSession().createQuery("from EvaluationBean as t");
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
	//�޸�Evaluation��Ϣ
	public void updateEvaluation(EvaluationBean eval)
	{
		sessionFactory.getCurrentSession().merge(eval);
	}
	//����ʦid��ѯ��¼������
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public int getRowsByTeacherid(String query) throws HibernateException{
		return ((Number)sessionFactory.getCurrentSession().createQuery(query).iterate().next()).intValue();
	}
	//����ʦid��ѯ��¼
	@SuppressWarnings("unchecked")
	public List<EvaluationBean> getEvaluationByTid(int tid){
		Query q=sessionFactory.getCurrentSession().createQuery("from EvaluationBean where teacherid=:tid");
		q.setInteger("tid", tid);
		List<EvaluationBean> list=q.list();
		return list;
	}

	
}
