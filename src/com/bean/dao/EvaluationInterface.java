package com.bean.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bean.domain.EvaluationBean;
import com.bean.domain.StudentBean;

public interface EvaluationInterface {

	//���Evaluation
	public abstract void addEvaluation(EvaluationBean eval);

	//ɾ��Evaluation
	public abstract void delEvaluation(int id);

	//��ѯEvaluation
	public abstract List<EvaluationBean> query();

	//��id��ѯEvaluation
	public abstract EvaluationBean queryById(int id);
	
	//��ȡ�������ۼ�¼����ҳ
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<EvaluationBean> findWithPage(int pageSize,int startRow) throws HibernateException;
	
	//��ȡ������
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public int getRows(String query) throws HibernateException;

	//�޸�Evaluation��Ϣ
	public abstract void updateEvaluation(EvaluationBean eval);
	
	//����ʦid��ѯ��¼������
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public int getRowsByTeacherid(String query) throws HibernateException;
	
	//����ʦid��ѯ��¼
	public List<EvaluationBean> getEvaluationByTid(int tid);

}