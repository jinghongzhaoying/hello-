package com.bean.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bean.domain.TeacherBean;


public interface TeacherInterface {

	public abstract void addTeacher(TeacherBean teacher);

	//ɾ��teacher��Ϣ

	public abstract void delTeacher(int id);

	//��ѯ������ʦ��Ϣ
	public abstract List<TeacherBean> query();
	
	//��ѯ������ߵ�ǰ������¼
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<TeacherBean> queryIndex();
	
	//��ȡ�������ۼ�¼����ҳ
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<TeacherBean> findWithPage(int pageSize,int startRow,String hql) throws HibernateException;
	
	//��ȡ������
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public int getRows(String query) throws HibernateException;

	//��id��ѯ��ʦ��Ϣ
	public abstract TeacherBean querById(int id);
	
     //�޸���ʦ��Ϣ
	public void updateTea(TeacherBean teacher);
	
	//��֤��½
	@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
	public TeacherBean login(String username,String password);

}