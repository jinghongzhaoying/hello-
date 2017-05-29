package com.bean.dao;

import java.util.List;

import com.bean.domain.LoginTypeBean;


public interface logintypeInterface {

	//添加login类型
	public  void addLoginType(LoginTypeBean logintype);

	//删除login类型
	public void delLoginType(int id);

	//查询login类型
	public List<LoginTypeBean> query();

	//按id查询login类型
	public LoginTypeBean querById(int id);

}