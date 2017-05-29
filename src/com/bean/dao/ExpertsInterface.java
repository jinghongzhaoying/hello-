package com.bean.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bean.domain.ExpertsBean;


public interface ExpertsInterface {

	public abstract void addExperts(ExpertsBean experts);

	//ɾ��experts��Ϣ

	public abstract void delExperts(int id);

	//��ѯ����experts��Ϣ
	public abstract List<ExpertsBean> query();

	//��id��ѯexperts��Ϣ
	public abstract ExpertsBean querById(int id);

	//��������ѯexperts��Ϣ
	public abstract List<ExpertsBean> querByExname(String exname);
	
	//��ȡ�������ۼ�¼����ҳ
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<ExpertsBean> findWithPage(int pageSize,int startRow,String hql) throws HibernateException;
	
	//��ȡ������
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public int getRows(String query) throws HibernateException;

	//�޸�experts��Ϣ
	public abstract void updateEx(ExpertsBean experts);

	//��֤��½
	@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
	public ExpertsBean login(String username,String password);

}