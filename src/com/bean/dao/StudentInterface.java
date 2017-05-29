package com.bean.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bean.domain.StudentBean;


public interface StudentInterface {

	public abstract void addStudent(StudentBean student);

	//ɾ��student��Ϣ

	public abstract void delStudent(int id);

	//��ѯ����ѧ����Ϣ
	public abstract List<StudentBean> query();

	//��id��ѯѧ����Ϣ

	public abstract StudentBean querById(int id);

	//��ѧ�Ų�ѯѧ����Ϣ
	public abstract StudentBean querByStuno(int stuno);

	//��������ѯѧ����Ϣ
	public abstract List<StudentBean> querByStuname(String stuname);
	
	//��ȡ�������ۼ�¼����ҳ
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<StudentBean> findWithPage(int pageSize,int startRow,String hql) throws HibernateException;
	
	//��ȡ������
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public int getRows(String query) throws HibernateException;
    //�޸�ѧ����Ϣ
	public void updateStu(StudentBean student);
	//��֤��½
	@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
	public StudentBean login(String username,String password) ;

}