package com.bean.dao;

import java.util.List;

import com.bean.domain.LoginTypeBean;


public interface logintypeInterface {

	//���login����
	public  void addLoginType(LoginTypeBean logintype);

	//ɾ��login����
	public void delLoginType(int id);

	//��ѯlogin����
	public List<LoginTypeBean> query();

	//��id��ѯlogin����
	public LoginTypeBean querById(int id);

}