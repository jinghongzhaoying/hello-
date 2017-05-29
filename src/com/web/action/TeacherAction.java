package com.web.action;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.bean.dao.EvaluationInterface;
import com.bean.dao.TeacherInterface;
import com.bean.domain.TeacherBean;
import com.util.helper.Pager;
import com.util.helper.PagerHelper;
import com.util.helper.ShowGrede;
import com.web.formbean.TeacherForm;

public class TeacherAction extends DispatchAction {
	@Resource private TeacherInterface teacherDao;
	@Resource private EvaluationInterface evalDao;

	//��ҳ��ѯ
	@SuppressWarnings("unchecked")
	public ActionForward queryIndex(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List list=teacherDao.queryIndex();
		request.setAttribute("TEACHERS", list);
		return mapping.findForward("queryIndex");
	}
	//��ҳ��ѯ
	public ActionForward queryWithPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TeacherForm teacher=(TeacherForm)form;
		String searchMethod=request.getParameter("searchMethod");
		String hql="";
		if(("all").equals(searchMethod)){
			hql="from TeacherBean as t";
		}else if(("name").equals(searchMethod)){
			hql="from TeacherBean as t where name like '%"+teacher.getName()+"%'";
		}else if(("course").equals(searchMethod)){
			hql="from TeacherBean as t where course like '%"+teacher.getCourse()+"%'";
		}else if(("score").equals(searchMethod)){
			hql="from TeacherBean as t where score>="+teacher.getScore();
		}else{
			hql="from TeacherBean as t";
		}
		int totalRows=teacherDao.getRows("select count(*) "+hql);
		Pager pager=PagerHelper.getPager(request, totalRows);
		List list=teacherDao.findWithPage(pager.getPageSize(), pager.getStartRow(),hql);
		request.setAttribute("TEACHERS", list);
		request.setAttribute("PAGER", pager);
		request.setAttribute("searchmethod", searchMethod);
		return mapping.findForward("querySuccess");
	}
	//�鿴��ʦ��ϸ��Ϣ
	public ActionForward showDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		int id=Integer.parseInt(request.getParameter("id"));
		TeacherBean teacher=teacherDao.querById(id);
		//��ȡ��������
		List list=evalDao.getEvaluationByTid(teacher.getId());
		request.setAttribute("remarklist", list);
		//���÷�����ʾ�취
		String gradepath=ShowGrede.getGradePath(teacher.getScore());
		request.setAttribute("gradepath", gradepath);
		
		request.setAttribute("teacher", teacher);
		return mapping.findForward("detail");
	}
	//���۽�ʦ
	public ActionForward goEvaluate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session=request.getSession();
		int id=Integer.parseInt(request.getParameter("tid"));
		TeacherBean teacher=teacherDao.querById(id);
		String returnType="";
		String type_id=session.getAttribute("typeid").toString();
		int typeid=0;
		if(type_id==null||type_id.equals("")){
			returnType="failure";
		}else{
			typeid=Integer.parseInt(type_id );
			switch (typeid) {
			case 1:
				returnType="gotype1";
				break;
			case 2:
				returnType="gotype2";
				break;
			case 3:
				returnType="gotype3";
				break;
			case 4:
				returnType="gotype4";
				break;
			case 5:
				returnType="gotype5";
				break;
			default:
				break;
			}
		}
		//���÷�����ʾ�취
		String gradepath=ShowGrede.getGradePath(teacher.getScore());
		System.out.println(gradepath);
		request.setAttribute("gradepath", gradepath);
		session.setAttribute("evalteacher", teacher);
		return mapping.findForward(returnType);
	}
	
	//���������ҳ��ѯ
	@SuppressWarnings("unchecked")
	public ActionForward MqueryIndex(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List list=teacherDao.queryIndex();
		request.setAttribute("MTEACHERS", list);
		return mapping.findForward("MqueryIndex");
	}
	//��������ҳ��ѯ
	public ActionForward MqueryWithPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TeacherForm teacher=(TeacherForm)form;
		String searchMethod=request.getParameter("searchMethod");
		String hql="";
		if(searchMethod.equals("all")){
			hql="from TeacherBean as t";
		}else if(searchMethod.equals("name")){
			hql="from TeacherBean as t where name like '%"+teacher.getName()+"%'";
		}else if(searchMethod.equals("course")){
			hql="from TeacherBean as t where course like '%"+teacher.getCourse()+"%'";
		}else if(searchMethod.equals("score")){
			hql="from TeacherBean as t where score>="+teacher.getScore();
		}else{
			hql="from TeacherBean as t";
		}
		int totalRows=teacherDao.getRows("select count(*) "+hql);
		Pager pager=PagerHelper.getPager(request, totalRows);
		List list=teacherDao.findWithPage(pager.getPageSize(), pager.getStartRow(),hql);
		request.setAttribute("TEACHERS", list);
		request.setAttribute("PAGER", pager);
		request.setAttribute("searchmethod", searchMethod);
		return mapping.findForward("MquerySuccess");
	}
	// �������鿴��ʦ��ϸ��Ϣ
	public ActionForward MshowDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		System.out.println(request.getParameter("id"));
		int id=Integer.parseInt(request.getParameter("id"));
		
		TeacherBean teacher=teacherDao.querById(id);
		request.setAttribute("teacher", teacher);
		return mapping.findForward("Mdetail");
	}
	//ɾ����ʦ��Ϣ
	public ActionForward DelTeacher(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		System.out.println(request.getParameter("id"));
		int id=Integer.parseInt(request.getParameter("id"));
		
		teacherDao.delTeacher(id);
	    String hql="from TeacherBean as t";
		int totalRows=teacherDao.getRows("select count(*) "+hql);
		Pager pager=PagerHelper.getPager(request, totalRows);
		List<TeacherBean> list=teacherDao.findWithPage(pager.getPageSize(), pager.getStartRow(),hql);
		request.setAttribute("TEACHERS", list);
		request.setAttribute("PAGER", pager);
		request.setAttribute("searchmethod", "all");
		return mapping.findForward("MquerySuccess");
		}
	//��ӽ�ʦ��Ϣ
	public ActionForward addTeacher(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		TeacherForm t1=(TeacherForm)form;
		TeacherBean t=new TeacherBean();
		t.setAge(t1.getAge());
		t.setCourse(t1.getCourse());
		t.setDegree(t1.getDegree());
		t.setEmail(t1.getEmail());
		t.setName(t1.getName());
		t.setPassword(t1.getPassword());
		t.setSex(t1.getSex());
		t.setTel(t1.getTel());
		t.setTypeid(t1.getTypeid());
	
		
		teacherDao.addTeacher(t);
	    String hql="from TeacherBean as t";
		int totalRows=teacherDao.getRows("select count(*) "+hql);
		Pager pager=PagerHelper.getPager(request, totalRows);
		List<TeacherBean> list=teacherDao.findWithPage(pager.getPageSize(), pager.getStartRow(),hql);
		request.setAttribute("TEACHERS", list);
		request.setAttribute("PAGER", pager);
		request.setAttribute("searchmethod", "all");
		return mapping.findForward("MquerySuccess");
		}

	
}
