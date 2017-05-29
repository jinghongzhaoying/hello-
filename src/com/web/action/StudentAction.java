package com.web.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import com.bean.dao.StudentInterface;
import com.bean.domain.StudentBean;
import com.util.helper.Pager;
import com.util.helper.PagerHelper;
import com.web.formbean.StudentForm;


public class StudentAction extends DispatchAction {
	@Resource private StudentInterface studentDao;
	//管理界面分页查询
	@SuppressWarnings("unchecked")
	public ActionForward MqueryWithPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StudentForm student=(StudentForm)form;
		String searchMethod=request.getParameter("searchMethod");
		String hql="";
		if(searchMethod.equals("all")){
			hql="from StudentBean as t";
		}else if(searchMethod.equals("name")){
			hql="from StudentBean as t where name like '%"+student.getName()+"%'";
		}else if(searchMethod.equals("stuno")){
			hql="from StudentBean as t where stuno="+student.getStuno();
		}else{
			hql="from StudentBean as t";
		}
		int totalRows=studentDao.getRows("select count(*) "+hql);
		Pager pager=PagerHelper.getPager(request, totalRows);
		List list=studentDao.findWithPage(pager.getPageSize(), pager.getStartRow(),hql);
		request.setAttribute("STUDENTS", list);
		request.setAttribute("PAGER", pager);
		request.setAttribute("searchmethod", searchMethod);
		return mapping.findForward("MquerySuccess");
	}
	// 管理界面查看学生详细信息
	public ActionForward MshowDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		System.out.println(request.getParameter("id"));
		int id=Integer.parseInt(request.getParameter("id"));
		
		StudentBean student=studentDao.querById(id);
		request.setAttribute("student",student);
		return mapping.findForward("Mdetail");
	}
	//删除学生信息
	public ActionForward DelStudent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		System.out.println(request.getParameter("id"));
		int id=Integer.parseInt(request.getParameter("id"));
		
		studentDao.delStudent(id);
	    String hql="from StudentBean as t";
		int totalRows=studentDao.getRows("select count(*) "+hql);
		Pager pager=PagerHelper.getPager(request, totalRows);
		List<StudentBean> list=studentDao.findWithPage(pager.getPageSize(), pager.getStartRow(),hql);
		request.setAttribute("STUDENTS", list);
		request.setAttribute("PAGER", pager);
		request.setAttribute("searchmethod", "all");
		return mapping.findForward("MquerySuccess");
		}
	//添加学生信息
	public ActionForward addStudent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		System.out.println("123");
		StudentForm t1=(StudentForm)form;
		StudentBean t=new StudentBean();
		t.setAge(t1.getAge());
		t.setAddress(t1.getAddress());
		t.setStuno(t1.getStuno());
		t.setName(t1.getName());
		t.setPassword(t1.getPassword());
		t.setSex(t1.getSex());
		t.setTel(t1.getTel());
		t.setTypeid(t1.getTypeid());
	
		
		studentDao.addStudent(t);
	    String hql="from StudentBean as t";
		int totalRows=studentDao.getRows("select count(*) "+hql);
		Pager pager=PagerHelper.getPager(request, totalRows);
		List<StudentBean> list=studentDao.findWithPage(pager.getPageSize(), pager.getStartRow(),hql);
		request.setAttribute("STUDENTS", list);
		request.setAttribute("PAGER", pager);
		request.setAttribute("searchmethod", "all");
		return mapping.findForward("MquerySuccess");
		}
}
