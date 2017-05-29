package com.bean.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bean.domain.LeaderBean;


public interface LeaderInterface {

	//���leader��Ϣ
	public abstract void addLeader(LeaderBean leader);

	//ɾ��leader��Ϣ

	public abstract void delLeader(int id);

	//��ѯ����leader��Ϣ
	public abstract List<LeaderBean> query();

	//��id��ѯleader��Ϣ

	public abstract LeaderBean querById(int id);

	//��������ѯleader��Ϣ
	public abstract List<LeaderBean> querBylename(String lename);
	
	//��ȡ�������ۼ�¼����ҳ
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<LeaderBean> findWithPage(int pageSize,int startRow,String hql) throws HibernateException;
	
	//��ȡ������
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public int getRows(String query) throws HibernateException;

	//�޸�leader��Ϣ

	public abstract void updateLe(LeaderBean leader);

	//��֤��½
	@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
	public LeaderBean login(String username,String password) ;

}