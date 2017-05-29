package com.web.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import com.bean.dao.ParentsInterface;
import com.bean.domain.ParentsBean;
import com.util.helper.Pager;
import com.util.helper.PagerHelper;
import com.web.formbean.ParentsForm;


public class ParentsAction extends DispatchAction {
	@Resource private ParentsInterface parentsDao;
	//管理界面分页查询
	@SuppressWarnings("unchecked")
	public ActionForward MqueryWithPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ParentsForm parents=(ParentsForm)form;
		String searchMethod=request.getParameter("searchMethod");
		String hql="";
		if(searchMethod.equals("all")){
			hql="from ParentsBean as t";
		}else if(searchMethod.equals("name")){
			hql="from ParentsBean as t where name like '%"+parents.getName()+"%'";
		}else{
			hql="from ParentsBean as t";
		}
		int totalRows=parentsDao.getRows("select count(*) "+hql);
		Pager pager=PagerHelper.getPager(request, totalRows);
		List list=parentsDao.findWithPage(pager.getPageSize(), pager.getStartRow(),hql);
		request.setAttribute("PARENTS", list);
		request.setAttribute("PAGER", pager);
		request.setAttribute("searchmethod", searchMethod);
		return mapping.findForward("MquerySuccess");
	}
	// 管理界面查看家长详细信息
	public ActionForward MshowDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		System.out.println(request.getParameter("id"));
		int id=Integer.parseInt(request.getParameter("id"));
		
		ParentsBean parents=parentsDao.querById(id);
		request.setAttribute("parents", parents);
		return mapping.findForward("Mdetail");
	}
	//删除家长信息
	public ActionForward DelParents(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		System.out.println(request.getParameter("id"));
		int id=Integer.parseInt(request.getParameter("id"));
		
		parentsDao.delParents(id);
	    String hql="from ParentsBean as t";
		int totalRows=parentsDao.getRows("select count(*) "+hql);
		Pager pager=PagerHelper.getPager(request, totalRows);
		List<ParentsBean> list=parentsDao.findWithPage(pager.getPageSize(), pager.getStartRow(),hql);
		request.setAttribute("PARENTS", list);
		request.setAttribute("PAGER", pager);
		request.setAttribute("searchmethod", "all");
		return mapping.findForward("MquerySuccess");
		}
	//添加家长信息
	public ActionForward addParents(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		System.out.println("123");
		ParentsForm t1=(ParentsForm)form;
		ParentsBean t=new ParentsBean();
		t.setName(t1.getName());
		t.setTel(t1.getTel());
		t.setTypeid(t1.getTypeid());
		t.setPassword(t1.getPassword());
	
		
		parentsDao.addParents(t);
	    String hql="from ParentsBean as t";
		int totalRows=parentsDao.getRows("select count(*) "+hql);
		Pager pager=PagerHelper.getPager(request, totalRows);
		List<ParentsBean> list=parentsDao.findWithPage(pager.getPageSize(), pager.getStartRow(),hql);
		request.setAttribute("PARENTS", list);
		request.setAttribute("PAGER", pager);
		request.setAttribute("searchmethod", "all");
		return mapping.findForward("MquerySuccess");
		}
}
