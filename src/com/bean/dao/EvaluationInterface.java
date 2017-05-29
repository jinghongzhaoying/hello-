package com.bean.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bean.domain.EvaluationBean;
import com.bean.domain.StudentBean;

public interface EvaluationInterface {

	//添加Evaluation
	public abstract void addEvaluation(EvaluationBean eval);

	//删除Evaluation
	public abstract void delEvaluation(int id);

	//查询Evaluation
	public abstract List<EvaluationBean> query();

	//按id查询Evaluation
	public abstract EvaluationBean queryById(int id);
	
	//获取所有销售记录，分页
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<EvaluationBean> findWithPage(int pageSize,int startRow) throws HibernateException;
	
	//获取总行数
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public int getRows(String query) throws HibernateException;

	//修改Evaluation信息
	public abstract void updateEvaluation(EvaluationBean eval);
	
	//按教师id查询记录的条数
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public int getRowsByTeacherid(String query) throws HibernateException;
	
	//按教师id查询记录
	public List<EvaluationBean> getEvaluationByTid(int tid);

}