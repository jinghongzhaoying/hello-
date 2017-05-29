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
   //添加Evaluation
	public void addEvaluation(EvaluationBean eval)
	{   
		sessionFactory.getCurrentSession().save(eval);
	}
	//删除Evaluation
	public void delEvaluation(int id)
	{
		sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().load(EvaluationBean.class,id));
	}
	//查询Evaluation
	@SuppressWarnings("unchecked")
	public List<EvaluationBean> query()
	{
		return (List<EvaluationBean>)sessionFactory.getCurrentSession().createQuery("from EvaluationBean").list();
	}
	//按id查询Evaluation
	public EvaluationBean queryById(int id)
	{
		return (EvaluationBean)sessionFactory.getCurrentSession().get(EvaluationBean.class,id);
	}
	//获取所有学生记录，分页
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
	//获取总行数
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public int getRows(String query) throws HibernateException{
		return ((Number)sessionFactory.getCurrentSession().createQuery(query).iterate().next()).intValue();
	}
	//修改Evaluation信息
	public void updateEvaluation(EvaluationBean eval)
	{
		sessionFactory.getCurrentSession().merge(eval);
	}
	//按教师id查询记录的条数
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public int getRowsByTeacherid(String query) throws HibernateException{
		return ((Number)sessionFactory.getCurrentSession().createQuery(query).iterate().next()).intValue();
	}
	//按教师id查询记录
	@SuppressWarnings("unchecked")
	public List<EvaluationBean> getEvaluationByTid(int tid){
		Query q=sessionFactory.getCurrentSession().createQuery("from EvaluationBean where teacherid=:tid");
		q.setInteger("tid", tid);
		List<EvaluationBean> list=q.list();
		return list;
	}

	
}
