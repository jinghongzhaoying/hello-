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
import com.bean.dao.ExpertsInterface;
import com.bean.dao.LeaderInterface;
import com.bean.dao.ParentsInterface;
import com.bean.dao.StudentInterface;
import com.bean.dao.TeacherInterface;
import com.bean.domain.ExpertsBean;
import com.bean.domain.LeaderBean;
import com.bean.domain.ParentsBean;
import com.bean.domain.StudentBean;
import com.bean.domain.TeacherBean;
import com.util.helper.ShowGrede;
import com.web.formbean.CenterForm;

public class PersonCenter extends DispatchAction {
	@Resource private StudentInterface studentDao;
	@Resource private ExpertsInterface expertsDao;
	@Resource private LeaderInterface leaderDao;
	@Resource private TeacherInterface teacherDao;
	@Resource private ParentsInterface parentsDao;
	@Resource private EvaluationInterface evalDao;
	//学生修改个人信息
	public ActionForward updStudent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session=request.getSession();
		int id=Integer.parseInt(request.getParameter("id"));
		CenterForm cf=(CenterForm)form;
		
		if(cf.getPassword().equals(cf.getPassword2())&&cf.getPassword2().equals(cf.getPassword())){
			StudentBean student=studentDao.querById(id);
			student.setName(cf.getName());
			student.setPassword(cf.getPassword());
			student.setTel(cf.getTel());
			student.setAge(cf.getAge());
			student.setAddress(cf.getAddress());
			studentDao.updateStu(student);//修改学生信息
			session.setAttribute("loginuser", student);
			return mapping.findForward("updstudent");
		}else{
			return mapping.findForward("failure");
		}		
	}
	//专家修改个人信息
	public ActionForward updExperts(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session=request.getSession();
		int id=Integer.parseInt(request.getParameter("id"));
		CenterForm cf=(CenterForm)form;
		
		if(cf.getPassword().equals(cf.getPassword2())&&cf.getPassword2().equals(cf.getPassword())){
			ExpertsBean experts=expertsDao.querById(id);
			experts.setName(cf.getName());
			experts.setPassword(cf.getPassword());
			experts.setAge(cf.getAge());
			experts.setTel(cf.getTel());
			experts.setEmail(cf.getEmail());
			experts.setMajor(cf.getMajor());
			session.setAttribute("loginuser", experts);
			return mapping.findForward("updexperts");
		}else{
			return mapping.findForward("failure");
		}		
	}
	//教师修改个人信息
	@SuppressWarnings("unchecked")
	public ActionForward updTeacher(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session=request.getSession();
		int id=Integer.parseInt(request.getParameter("id"));
		CenterForm cf=(CenterForm)form;
		
		if(cf.getPassword().equals(cf.getPassword2())&&cf.getPassword2().equals(cf.getPassword())){
			TeacherBean teacher=teacherDao.querById(id);
			teacher.setName(cf.getName());
			teacher.setPassword(cf.getPassword());
			teacher.setAge(cf.getAge());
			teacher.setTel(cf.getTel());
			teacher.setEmail(cf.getEmail());
			teacher.setDegree(cf.getDegree());
			session.setAttribute("loginuser", teacher);
			//获取评价留言
			List list=evalDao.getEvaluationByTid(teacher.getId());
			request.setAttribute("remarklist", list);
			//调用分数显示办法
			String gradepath=ShowGrede.getGradePath(teacher.getScore());
			request.setAttribute("gradepath", gradepath);
			
			request.setAttribute("teacher", teacher);
			return mapping.findForward("updteacher");
		}else{
			return mapping.findForward("failure");
		}		
	}
	//领导修改个人信息
	public ActionForward updLeader(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session=request.getSession();
		int id=Integer.parseInt(request.getParameter("id"));
		CenterForm cf=(CenterForm)form;
		
		if(cf.getPassword().equals(cf.getPassword2())&&cf.getPassword2().equals(cf.getPassword())){
			LeaderBean leader=leaderDao.querById(id);
			leader.setName(cf.getName());
			leader.setPassword(cf.getPassword());
			leader.setAge(cf.getAge());
			leader.setTel(cf.getTel());
			leader.setEmail(cf.getEmail());
			session.setAttribute("loginuser", leader);
			return mapping.findForward("updleader");
		}else{
			return mapping.findForward("failure");
		}		
	}
	//家长修改个人信息
	public ActionForward updParents(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session=request.getSession();
		int id=Integer.parseInt(request.getParameter("id"));
		CenterForm cf=(CenterForm)form;
		
		if(cf.getPassword().equals(cf.getPassword2())&&cf.getPassword2().equals(cf.getPassword())){
			ParentsBean parents=parentsDao.querById(id);
			parents.setName(cf.getName());
			parents.setPassword(cf.getPassword());
			parents.setTel(cf.getTel());
			session.setAttribute("loginuser", parents);
			return mapping.findForward("updparents");
		}else{
			return mapping.findForward("failure");
		}		
	}
}
