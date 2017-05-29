package com.bean.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bean.dao.AdminInterface;
import com.bean.domain.AdminBean;
@Transactional
public class AdminDao implements AdminInterface  {
	@Resource private SessionFactory sessionFactory;
   //添加admin
	/* (non-Javadoc)
	 * @see com.bean.dao.impl.AdminInterface#addAdmin(com.web.Formbean.AdminBean)
	 */
	public void addAdmin(AdminBean admin)
	{   

		sessionFactory.getCurrentSession().save(admin);

	}
	//删除admin
	/* (non-Javadoc)
	 * @see com.bean.dao.impl.AdminInterface#delAdmin(int)
	 */
	public void delAdmin(int id)
	{
		sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().load(AdminBean.class,id));
	}
	//查询admin
	/* (non-Javadoc)
	 * @see com.bean.dao.impl.AdminInterface#query()
	 */
	@SuppressWarnings("unchecked")
	public List<AdminBean> query()
	{
		return (List<AdminBean>)sessionFactory.getCurrentSession().createQuery("from admin").list();
	}
	//按id查询admin
	/* (non-Javadoc)
	 * @see com.bean.dao.impl.AdminInterface#querById(int)
	 */
	public AdminBean querById(int id)
	{
		return (AdminBean)sessionFactory.getCurrentSession().get(AdminBean.class,id);
	}
	//修改admin信息

	/* (non-Javadoc)
	 * @see com.bean.dao.impl.AdminInterface#updateAdmin(com.web.Formbean.AdminBean)
	 */
	public void updateAdmin(AdminBean admin)
	{
		sessionFactory.getCurrentSession().merge(admin);
	}
	//验证登陆
	@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
	public AdminBean login(String username,String password) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("from AdminBean where name=:name and password=:pwd");
		q.setString("name", username);
		q.setString("pwd", password);
		AdminBean loginuser = (AdminBean)q.uniqueResult();
		if(loginuser != null){
			Hibernate.initialize(loginuser.getTypeid());
		}
		return loginuser;
	}
}
