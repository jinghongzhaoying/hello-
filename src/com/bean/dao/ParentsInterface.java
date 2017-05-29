package com.bean.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bean.domain.ParentsBean;


public interface ParentsInterface {

	//���Parents��Ϣ
	public abstract void addParents(ParentsBean parents);

	//ɾ��parents��Ϣ
	public abstract void delParents(int id);

	//��ѯ����parents��Ϣ
	public abstract List<ParentsBean> query();

	//��id��ѯparents��Ϣ
	public abstract ParentsBean querById(int id);

	//��������ѯparents��Ϣ
	public abstract List<ParentsBean> querByParname(String parname);
	
	//��ȡ�������ۼ�¼����ҳ
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<ParentsBean> findWithPage(int pageSize,int startRow,String hql) throws HibernateException;
	
	//��ȡ������
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public int getRows(String query) throws HibernateException;

	//�޸�parents��Ϣ
	public abstract void updatePar(ParentsBean parents);

	//��֤��½
	@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
	public ParentsBean login(String username,String password);

}