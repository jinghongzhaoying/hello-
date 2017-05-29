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

import com.bean.dao.StudentInterface;
import com.bean.domain.StudentBean;

@Transactional
public class StudentDao implements StudentInterface {
	@Resource private SessionFactory sessionFactory;
   //���student��Ϣ
	public void addStudent(StudentBean student)
	{   

		sessionFactory.getCurrentSession().save(student);

	}
	//ɾ��student��Ϣ
	public void delStudent(int id)
	{
		sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().load(StudentBean.class,id));
	}
	//��ѯ����ѧ����Ϣ
	@SuppressWarnings("unchecked")
	public List<StudentBean> query()
	{
		return (List<StudentBean>)sessionFactory.getCurrentSession().createQuery("from student").list();
	}
	//��id��ѯѧ����Ϣ
	public StudentBean querById(int id)
	{
		return (StudentBean)sessionFactory.getCurrentSession().get(StudentBean.class,id);
	}
	//��ѧ�Ų�ѯѧ����Ϣ
	public StudentBean querByStuno(int stuno)
	{
		return (StudentBean)sessionFactory.getCurrentSession().get(StudentBean.class,stuno);
	}
	//��������ѯѧ����Ϣ
	@SuppressWarnings("unchecked")
	public List<StudentBean> querByStuname(String stuname)
	{
		return (List<StudentBean>)sessionFactory.getCurrentSession().createSQLQuery( "select * from student where name like ?" ).setParameter(0, stuname).list();

	}
	//��ȡ����ѧ����¼����ҳ
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	@SuppressWarnings("unchecked")
	public List<StudentBean> findWithPage(int pageSize,int startRow,String hql) throws HibernateException{
		List<StudentBean> recordList=null;
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
	//�޸�ѧ����Ϣ
	public void updateStu(StudentBean student)
	{
		sessionFactory.getCurrentSession().merge(student);
	}
	//��֤��½
	@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
	public StudentBean login(String username,String password) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from StudentBean where name=:name and password=:pwd");
		q.setString("name", username);
		q.setString("pwd", password);
		StudentBean loginuser = (StudentBean)q.uniqueResult();
		if(loginuser != null){
			Hibernate.initialize(loginuser.getTypeid());
		}
		return loginuser;
	}
}
