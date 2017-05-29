package com.bean.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bean.domain.ExpertsBean;


public interface ExpertsInterface {

	public abstract void addExperts(ExpertsBean experts);

	//删除experts信息

	public abstract void delExperts(int id);

	//查询所有experts信息
	public abstract List<ExpertsBean> query();

	//按id查询experts信息
	public abstract ExpertsBean querById(int id);

	//按姓名查询experts信息
	public abstract List<ExpertsBean> querByExname(String exname);
	
	//获取所有销售记录，分页
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<ExpertsBean> findWithPage(int pageSize,int startRow,String hql) throws HibernateException;
	
	//获取总行数
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public int getRows(String query) throws HibernateException;

	//修改experts信息
	public abstract void updateEx(ExpertsBean experts);

	//验证登陆
	@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
	public ExpertsBean login(String username,String password);

}