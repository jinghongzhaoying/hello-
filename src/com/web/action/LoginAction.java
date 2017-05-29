package com.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.bean.dao.AdminInterface;
import com.bean.dao.EvaluationInterface;
import com.bean.dao.ExpertsInterface;
import com.bean.dao.LeaderInterface;
import com.bean.dao.ParentsInterface;
import com.bean.dao.StudentInterface;
import com.bean.dao.TeacherInterface;
import com.bean.domain.AdminBean;
import com.bean.domain.ExpertsBean;
import com.bean.domain.LeaderBean;
import com.bean.domain.ParentsBean;
import com.bean.domain.StudentBean;
import com.bean.domain.TeacherBean;
import com.util.helper.ShowGrede;
import com.web.formbean.LoginForm;

public class LoginAction  extends DispatchAction {
	@Resource private StudentInterface studentDao;
	@Resource private TeacherInterface teacherDao;
	@Resource private LeaderInterface leaderDao;
	@Resource private ExpertsInterface expertsDao;
	@Resource private ParentsInterface parentsDao;
	@Resource private AdminInterface adminDao;
	@Resource private EvaluationInterface evalDao;

	//�û���¼
	@SuppressWarnings("unchecked")
	public ActionForward userlogin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LoginForm login=(LoginForm)form;
		HttpSession session = request.getSession();
		String rType="";
		
		//�ж�form�Ƿ�Ϊ��
		if(login.getUsername()!=null&&login.getPassword()!=null&&login.getUsertype()!=null 
				&& !login.getUsername().equals("") && !login.getPassword().equals("")){
			int typeid=Integer.parseInt(login.getUsertype());
			switch (typeid) {
			case 1://ѧ��
				StudentBean student=studentDao.login(login.getUsername(), login.getPassword());
				if(student==null){
					return mapping.findForward("loginerror");
				}
				session.setAttribute("loginuser", student);
				rType="type1";
				break;
			case 2://��ʦ
				TeacherBean teacher=teacherDao.login(login.getUsername(), login.getPassword());
				if(teacher==null){
					return mapping.findForward("loginerror");
				}
				session.setAttribute("loginuser", teacher);
				//�������ʦ�������²���
				List list=evalDao.getEvaluationByTid(teacher.getId());//��ȡ��������
				request.setAttribute("remarklist",list);
				String gradepath=ShowGrede.getGradePath(teacher.getScore());//���÷�����ʾ�취
				request.setAttribute("gradepath", gradepath);
				request.setAttribute("teacher", teacher);
				rType="type2";
				break;
			case 3://�쵼
				LeaderBean leader=leaderDao.login(login.getUsername(), login.getPassword());
				if(leader==null){
					return mapping.findForward("loginerror");
				}
				session.setAttribute("loginuser", leader);
				rType="type3";
				break;
			case 4://ר��
				ExpertsBean experts=expertsDao.login(login.getUsername(), login.getPassword());
				if(experts==null){
					return mapping.findForward("loginerror");
				}
				session.setAttribute("loginuser", experts);
				rType="type4";
				break;
			case 5://�ҳ�
				ParentsBean parents=parentsDao.login(login.getUsername(), login.getPassword());
				if(parents==null){
					return mapping.findForward("loginerror");
				}
				session.setAttribute("loginuser", parents);
				rType="type5";
				break;
			case 6://����Ա
				AdminBean admin=adminDao.login(login.getUsername(), login.getPassword());
				if(admin==null){
					return mapping.findForward("loginerror");
				}
				session.setAttribute("loginuser", admin);
				rType="type6";
				break;
			default:
				break;
			}
			session.setAttribute("typeid", login.getUsertype());
			return mapping.findForward(rType);
		}
		else{
			return mapping.findForward("loginerror");
		}
	}
	//�ȵ�¼������
	public ActionForward logineval(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			LoginForm login=(LoginForm)form;
			StringBuilder ret = new StringBuilder();
			ret.append("({id:'").append(login.getId()).append("'})");
			System.out.println(ret);
			out.println(ret);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	//���۵�¼
	public ActionForward evallogin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LoginForm login=(LoginForm)form;
		HttpSession session = request.getSession();
		String returnType="";
		
		//�ж�form�Ƿ�Ϊ��
		if(login.getUsername()!=null&&login.getPassword()!=null&&login.getUsertype()!=null){
			int typeid=Integer.parseInt(login.getUsertype());
			switch (typeid) {
			case 1://ѧ��
				StudentBean student=studentDao.login(login.getUsername(), login.getPassword());
				session.setAttribute("loginuser", student);
				returnType="evallogintype1";
				break;
			case 2://��ʦ
				TeacherBean teacher=teacherDao.login(login.getUsername(), login.getPassword());
				session.setAttribute("loginuser", teacher);
				returnType="evallogintype2";
				break;
			case 3://�쵼
				LeaderBean leader=leaderDao.login(login.getUsername(), login.getPassword());
				session.setAttribute("loginuser", leader);
				returnType="evallogintype3";
				break;
			case 4://ר��
				ExpertsBean experts=expertsDao.login(login.getUsername(), login.getPassword());
				session.setAttribute("loginuser", experts);
				returnType="evallogintype4";
				break;
			case 5://�ҳ�
				ParentsBean parents=parentsDao.login(login.getUsername(), login.getPassword());
				session.setAttribute("loginuser", parents);
				returnType="evallogintype5";
				break;
			default:
				break;
			}
			TeacherBean teacher=teacherDao.querById(login.getId());
			//���÷�����ʾ�취
			String gradepath=ShowGrede.getGradePath(teacher.getScore());
			request.setAttribute("gradepath", gradepath);
			request.setAttribute("evalteacher", teacher);
			session.setAttribute("typeid", login.getUsertype());
			return mapping.findForward(returnType);
		}
		else{
			return mapping.findForward("loginerror");
		}
	}
	//�����������
	public ActionForward goCenter(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session=request.getSession();
		String returnStr="";
		if(session.getAttribute("loginuser")==null){
			returnStr="loginerror";
		}else{
			int typeid=Integer.parseInt(session.getAttribute("typeid").toString());
			switch (typeid) {
			case 1:
				returnStr="type1center";
				break;
			case 2:
				returnStr="type2center";
				break;
			case 3:
				returnStr="type3center";
				break;
			case 4:
				returnStr="type4center";
				break;
			case 5:
				returnStr="type5center";
				break;
			case 6:
				returnStr="type6center";
				break;
			default:
				break;
			}
		}
		return mapping.findForward(returnStr);
	}
}
