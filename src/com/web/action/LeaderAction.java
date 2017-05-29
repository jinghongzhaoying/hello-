package com.web.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import com.bean.dao.LeaderInterface;
import com.bean.domain.LeaderBean;
import com.util.helper.Pager;
import com.util.helper.PagerHelper;
import com.web.formbean.LeaderForm;


public class LeaderAction extends DispatchAction {
	@Resource private LeaderInterface leaderDao;
	//管理界面分页查询
	@SuppressWarnings("unchecked")
	public ActionForward MqueryWithPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LeaderForm leader=(LeaderForm)form;
		String searchMethod=request.getParameter("searchMethod");
		String hql="";
		if(searchMethod.equals("all")){
			hql="from LeaderBean as t";
		}else if(searchMethod.equals("name")){
			hql="from LeaderBean as t where name like '%"+leader.getName()+"%'";
		}else{
			hql="from LeaderBean as t";
		}
		int totalRows=leaderDao.getRows("select count(*) "+hql);
		Pager pager=PagerHelper.getPager(request, totalRows);
		List list=leaderDao.findWithPage(pager.getPageSize(), pager.getStartRow(),hql);
		request.setAttribute("LEADERS", list);
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
		
		LeaderBean leader=leaderDao.querById(id);
		request.setAttribute("leader",leader);
		return mapping.findForward("Mdetail");
	}
	//删除学生信息
	public ActionForward DelLeader(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		System.out.println(request.getParameter("id"));
		int id=Integer.parseInt(request.getParameter("id"));
		
		leaderDao.delLeader(id);
	    String hql="from LeaderBean as t";
		int totalRows=leaderDao.getRows("select count(*) "+hql);
		Pager pager=PagerHelper.getPager(request, totalRows);
		List<LeaderBean> list=leaderDao.findWithPage(pager.getPageSize(), pager.getStartRow(),hql);
		request.setAttribute("LEADERS", list);
		request.setAttribute("PAGER", pager);
		request.setAttribute("searchmethod", "all");
		return mapping.findForward("MquerySuccess");
		}
	//添加学生信息
	public ActionForward addLeader(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		System.out.println("123");
		LeaderForm t1=(LeaderForm)form;
		LeaderBean t=new LeaderBean();
		t.setTypeid(t1.getTypeid());
		t.setName(t1.getName());
		t.setPassword(t1.getPassword());
		t.setAge(t1.getAge());
		t.setSex(t1.getSex());
		t.setTel(t1.getTel());
		t.setEmail(t1.getEmail());
		t.setPosition(t1.getPosition());
	    leaderDao.addLeader(t);
	    String hql="from LeaderBean as t";
		int totalRows=leaderDao.getRows("select count(*) "+hql);
		Pager pager=PagerHelper.getPager(request, totalRows);
		List<LeaderBean> list=leaderDao.findWithPage(pager.getPageSize(), pager.getStartRow(),hql);
		request.setAttribute("LEADERS", list);
		request.setAttribute("PAGER", pager);
		request.setAttribute("searchmethod", "all");
		return mapping.findForward("MquerySuccess");
		}
}
