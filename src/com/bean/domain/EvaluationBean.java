package com.bean.domain;

import java.util.Date;

public class EvaluationBean {
	private int id;
	private int teacherid;//�����۵���ʦ��id
	private int evalid;//������id
	private int evaltypeid;//���������id
	private float evalscore;//���۵÷�
	private String remark;//��������
	private Date datetime;//����ʱ��
	public int getEvaltypeid() {
		return evaltypeid;
	}
	public void setEvaltypeid(int evaltypeid) {
		this.evaltypeid = evaltypeid;
	}
	public float getEvalscore() {
		return evalscore;
	}
	public void setEvalscore(float evalscore) {
		this.evalscore = evalscore;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTeacherid() {
		return teacherid;
	}
	public void setTeacherid(int teacherid) {
		this.teacherid = teacherid;
	}
	public int getEvalid() {
		return evalid;
	}
	public void setEvalid(int evalid) {
		this.evalid = evalid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

}
