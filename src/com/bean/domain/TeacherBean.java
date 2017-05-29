package com.bean.domain;

public class TeacherBean{
	private int id;
	private int typeid;//身份编号
	private String name;//姓名
	private String password;//登录密码
	private int age;//年龄
	private String sex;//性别
	private String tel;//电话
	private String email;//邮箱
	private String course;//授课名
	private String degree;//学位
	private float score;//分数
	private int evalsum;//评价人数
	
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
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
}
