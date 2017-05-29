package com.web.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import com.bean.dao.ExpertsInterface;
import com.bean.domain.ExpertsBean;
import com.util.helper.Pager;
import com.util.helper.PagerHelper;
import com.web.formbean.ExpertsForm;


public class ExpertsAction extends DispatchAction {
	@Resource private ExpertsInterface expertsDao;
	//管理界面分页查询
	@SuppressWarnings("unchecked")
	public ActionForward MqueryWithPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExpertsForm experts=(ExpertsForm)form;
		String searchMethod=request.getParameter("searchMethod");
		String hql="";
		if(searchMethod.equals("all")){
			hql="from ExpertsBean as t";
		}else if(searchMethod.equals("name")){
			hql="from ExpertsBean as t where name like '%"+experts.getName()+"%'";
		}else{
			hql="from ExpertsBean as t";
		}
		int totalRows=expertsDao.getRows("select count(*) "+hql);
		Pager pager=PagerHelper.getPager(request, totalRows);
		List list=expertsDao.findWithPage(pager.getPageSize(), pager.getStartRow(),hql);
		request.setAttribute("EXPERTS", list);
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
		
		ExpertsBean experts=expertsDao.querById(id);
		request.setAttribute("experts",experts);
		return mapping.findForward("Mdetail");
	}
	//删除学生信息
	public ActionForward DelExperts(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		System.out.println(request.getParameter("id"));
		int id=Integer.parseInt(request.getParameter("id"));
		
		expertsDao.delExperts(id);
	    String hql="from ExpertsBean as t";
		int totalRows=expertsDao.getRows("select count(*) "+hql);
		Pager pager=PagerHelper.getPager(request, totalRows);
		List<ExpertsBean> list=expertsDao.findWithPage(pager.getPageSize(), pager.getStartRow(),hql);
		request.setAttribute("EXPERTS", list);
		request.setAttribute("PAGER", pager);
		request.setAttribute("searchmethod", "all");
		return mapping.findForward("MquerySuccess");
		}
	//添加学生信息
	public ActionForward addExperts(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		System.out.println("123");
		ExpertsForm t1=(ExpertsForm)form;
		ExpertsBean t=new ExpertsBean();
		t.setTypeid(t1.getTypeid());
		t.setName(t1.getName());
		t.setPassword(t1.getPassword());
		t.setAge(t1.getAge());
		t.setSex(t1.getSex());
		t.setTel(t1.getTel());
		t.setEmail(t1.getEmail());
	   t.setMajor(t1.getMajor());
	    expertsDao.addExperts(t);
	    String hql="from ExpertsBean as t";
		int totalRows=expertsDao.getRows("select count(*) "+hql);
		Pager pager=PagerHelper.getPager(request, totalRows);
		List<ExpertsBean> list=expertsDao.findWithPage(pager.getPageSize(), pager.getStartRow(),hql);
		request.setAttribute("EXPERTS", list);
		request.setAttribute("PAGER", pager);
		request.setAttribute("searchmethod", "all");
		return mapping.findForward("MquerySuccess");
		}
}
