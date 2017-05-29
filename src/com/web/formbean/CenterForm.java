package com.web.formbean;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class CenterForm extends ActionForm {
	private int id;
	private int typeid;//身份编号
	private String name;//姓名
	private String password;//密码
	private String password2;//确认密码
	private String stuno;//学号
	private int age;//年龄
	private String sex;//性别
	private String tel;//电话
	private String address;//地址
	private String email;//邮编
	private String major;//专业
	
	private String course;//授课名
	private String degree;//学位
	private float score;//分数
	private int evalsum;//评价人数
	
	private String position;//职位
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public int getEvalsum() {
		return evalsum;
	}
	public void setEvalsum(int evalsum) {
		this.evalsum = evalsum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
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
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getStuno() {
		return stuno;
	}
	public void setStuno(String stuno) {
		this.stuno = stuno;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
