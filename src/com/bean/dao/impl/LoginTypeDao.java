package com.bean.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.bean.dao.logintypeInterface;
import com.bean.domain.LoginTypeBean;

@Transactional
public class LoginTypeDao implements logintypeInterface {
	@Resource private SessionFactory sessionFactory;
   //添加login类型
	
	public void addLoginType(LoginTypeBean login)
	{   

		sessionFactory.getCurrentSession().save(login);

	}
	//删除login类型
	
	public void delLoginType(int id)
	{
		sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().load(LoginTypeBean.class,id));
	}
	//查询login类型
	
	@SuppressWarnings("unchecked")
	public List<LoginTypeBean> query()
	{
		return (List<LoginTypeBean>)sessionFactory.getCurrentSession().createQuery("from logintype").list();
	}
	//按id查询login类型
	
	public LoginTypeBean querById(int id)
	{
		return (LoginTypeBean)sessionFactory.getCurrentSession().get(LoginTypeBean.class,id);
	}
}
