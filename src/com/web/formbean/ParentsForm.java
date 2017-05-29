package com.web.formbean;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class ParentsForm extends ActionForm{
private int id;
private int typeid;//身份编号
private String name;//姓名
private String password;//密码
private String tel;//电话
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getTypeid() {
	return typeid;
}
public void setTypeid(int typeid) {
	this.typeid = typeid;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}
}
