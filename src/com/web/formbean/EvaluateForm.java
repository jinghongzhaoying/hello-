package com.web.formbean;

import java.util.Date;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class EvaluateForm extends ActionForm {
	private float num1;
	private float num2;
	private float num3;
	private float num4;
	private float num5;
	private float num6;
	private float num7;
	private float num8;
	private float num9;
	private float num10;

	private int id;
	private int teacherid;//被评价的老师的id
	private int evalid;//评价人id
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
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	private int evaltypeid;//评价人身份id
	private float evalscore;//评价得分
	private String remark;//评价内容
	private Date datetime;//评价时间
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public float getNum1() {
		return num1;
	}
	public void setNum1(float num1) {
		this.num1 = num1;
	}
	public float getNum2() {
		return num2;
	}
	public void setNum2(float num2) {
		this.num2 = num2;
	}
	public float getNum3() {
		return num3;
	}
	public void setNum3(float num3) {
		this.num3 = num3;
	}
	public float getNum4() {
		return num4;
	}
	public void setNum4(float num4) {
		this.num4 = num4;
	}
	public float getNum5() {
		return num5;
	}
	public void setNum5(float num5) {
		this.num5 = num5;
	}
	public float getNum6() {
		return num6;
	}
	public void setNum6(float num6) {
		this.num6 = num6;
	}
	public float getNum7() {
		return num7;
	}
	public void setNum7(float num7) {
		this.num7 = num7;
	}
	public float getNum8() {
		return num8;
	}
	public void setNum8(float num8) {
		this.num8 = num8;
	}
	public float getNum9() {
		return num9;
	}
	public void setNum9(float num9) {
		this.num9 = num9;
	}
	public float getNum10() {
		return num10;
	}
	public void setNum10(float num10) {
		this.num10 = num10;
	}

}
