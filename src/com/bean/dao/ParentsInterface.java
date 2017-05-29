package com.bean.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bean.domain.ParentsBean;


public interface ParentsInterface {

	//添加Parents信息
	public abstract void addParents(ParentsBean parents);

	//删除parents信息
	public abstract void delParents(int id);

	//查询所有parents信息
	public abstract List<ParentsBean> query();

	//按id查询parents信息
	public abstract ParentsBean querById(int id);

	//按姓名查询parents信息
	public abstract List<ParentsBean> querByParname(String parname);
	
	//获取所有销售记录，分页
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<ParentsBean> findWithPage(int pageSize,int startRow,String hql) throws HibernateException;
	
	//获取总行数
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public int getRows(String query) throws HibernateException;

	//修改parents信息
	public abstract void updatePar(ParentsBean parents);

	//验证登陆
	@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
	public ParentsBean login(String username,String password);

}