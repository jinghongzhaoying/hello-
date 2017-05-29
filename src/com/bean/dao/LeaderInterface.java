package com.bean.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bean.domain.LeaderBean;


public interface LeaderInterface {

	//添加leader信息
	public abstract void addLeader(LeaderBean leader);

	//删除leader信息

	public abstract void delLeader(int id);

	//查询所有leader信息
	public abstract List<LeaderBean> query();

	//按id查询leader信息

	public abstract LeaderBean querById(int id);

	//按姓名查询leader信息
	public abstract List<LeaderBean> querBylename(String lename);
	
	//获取所有销售记录，分页
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<LeaderBean> findWithPage(int pageSize,int startRow,String hql) throws HibernateException;
	
	//获取总行数
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public int getRows(String query) throws HibernateException;

	//修改leader信息

	public abstract void updateLe(LeaderBean leader);

	//验证登陆
	@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
	public LeaderBean login(String username,String password) ;

}