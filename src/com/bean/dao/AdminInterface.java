package com.bean.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bean.domain.AdminBean;


public interface AdminInterface {

	public abstract void addAdmin(AdminBean admin);

	//ɾ��admin
	public abstract void delAdmin(int id);

	//��ѯadmin����
	public abstract List<AdminBean> query();

	//��id��ѯlogin����
	public abstract AdminBean querById(int id);

	//�޸�admin��Ϣ
	public abstract void updateAdmin(AdminBean admin);

	//��֤��½
	@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
	public AdminBean login(String username,String password);
}