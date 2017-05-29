package com.bean.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bean.domain.AdminBean;


public interface AdminInterface {

	public abstract void addAdmin(AdminBean admin);

	//删除admin
	public abstract void delAdmin(int id);

	//查询admin类型
	public abstract List<AdminBean> query();

	//按id查询login类型
	public abstract AdminBean querById(int id);

	//修改admin信息
	public abstract void updateAdmin(AdminBean admin);

	//验证登陆
	@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
	public AdminBean login(String username,String password);
}