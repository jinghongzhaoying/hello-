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
import com.bean.domain.EvaluationBean;
import com.bean.domain.ExpertsBean;
import com.bean.domain.LeaderBean;
import com.bean.domain.ParentsBean;
import com.bean.domain.StudentBean;
import com.bean.domain.TeacherBean;
import com.util.helper.GetTime;
import com.util.helper.Pager;
import com.util.helper.PagerHelper;
import com.util.helper.ShowGrede;
import com.web.formbean.EvaluateForm;

public class EvaluateAction extends DispatchAction {
	@Resource private TeacherInterface teacherDao;
	@Resource private EvaluationInterface evaluateDao;

	//学生评价处理
	@SuppressWarnings("unchecked")
	public ActionForward studentEval(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		EvaluateForm evalForm=(EvaluateForm)form;
		float totalScore=evalForm.getNum1()+evalForm.getNum2()+evalForm.getNum3()+evalForm.getNum4()
						+evalForm.getNum5()+evalForm.getNum6()+evalForm.getNum7()+evalForm.getNum8()
						+evalForm.getNum9()+evalForm.getNum10();//获取页面评分的总分
				
		int id=Integer.parseInt(request.getParameter("tid"));//要评价的老师
		TeacherBean teacher=teacherDao.querById(id);
		String hql="select count(*) from EvaluationBean where teacherid="+teacher.getId();
		int evalnum=evaluateDao.getRowsByTeacherid(hql);
				
		float updScore=teacher.getScore()+totalScore*30/100;//学生评价占30%的比例
		teacher.setScore((teacher.getScore()*evalnum+updScore)/(evalnum+1));//修改分数
		teacher.setEvalsum(evalnum+1);//修改评价人数，增加一人
		teacherDao.updateTea(teacher);
		
		StudentBean loginuser=(StudentBean)session.getAttribute("loginuser");//登录人员
		
		EvaluationBean evaluate=new EvaluationBean();
		evaluate.setTeacherid(teacher.getId());
		evaluate.setEvalid(loginuser.getId());
		evaluate.setEvaltypeid(loginuser.getTypeid());
		evaluate.setEvalscore(updScore);
		evaluate.setRemark(evalForm.getRemark());
		evaluate.setDatetime(GetTime.getTime());
		evaluateDao.addEvaluation(evaluate);//添加评价信息
		//获取评价留言
		List<Object> list=evaluateDao.getEvaluationByTid(teacher.getId());
		request.setAttribute("remarklist", list);
		//调用分数显示办法
		String gradepath=ShowGrede.getGradePath(teacher.getScore());
		request.setAttribute("gradepath", gradepath);
		request.setAttribute("teacher", teacher);
		return mapping.findForward("Success");
	}
	//同行评价
	@SuppressWarnings("unchecked")
	public ActionForward teacherEval(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		EvaluateForm evalForm=(EvaluateForm)form;
		float totalScore=evalForm.getNum1()+evalForm.getNum2()+evalForm.getNum3()+evalForm.getNum4()
						+evalForm.getNum5()+evalForm.getNum6()+evalForm.getNum7();//获取页面评分的总分
				
		int id=Integer.parseInt(request.getParameter("tid"));//要评价的老师
		TeacherBean teacher=teacherDao.querById(id);
		String hql="select count(*) from EvaluationBean where teacherid="+teacher.getId();
		int evalnum=evaluateDao.getRowsByTeacherid(hql);
				
		float updScore=teacher.getScore()+totalScore*15/100;//学生评价占15%的比例
		teacher.setScore((teacher.getScore()*evalnum+updScore)/(evalnum+1));//修改分数
		teacher.setEvalsum(evalnum+1);//修改评价人数，增加一人
		teacherDao.updateTea(teacher);
		
		TeacherBean loginuser=(TeacherBean)session.getAttribute("loginuser");//登录人员
		
		EvaluationBean evaluate=new EvaluationBean();
		evaluate.setTeacherid(teacher.getId());
		evaluate.setEvalid(loginuser.getId());
		evaluate.setEvaltypeid(loginuser.getTypeid());
		evaluate.setEvalscore(updScore);
		evaluate.setRemark(evalForm.getRemark());
		evaluate.setDatetime(GetTime.getTime());
		evaluateDao.addEvaluation(evaluate);//添加评价信息
		
		//获取评价留言
		List list=evaluateDao.getEvaluationByTid(teacher.getId());
		request.setAttribute("remarklist", list);
		//调用分数显示办法
		String gradepath=ShowGrede.getGradePath(teacher.getScore());
		request.setAttribute("gradepath", gradepath);
		request.setAttribute("teacher", teacher);
		return mapping.findForward("Success");
	}
	//专家评价
	@SuppressWarnings("unchecked")
	public ActionForward expertsEval(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		EvaluateForm evalForm=(EvaluateForm)form;
		float totalScore=evalForm.getNum1()+evalForm.getNum2()+evalForm.getNum3()+evalForm.getNum4()
						+evalForm.getNum5();//获取页面评分的总分
				
		int id=Integer.parseInt(request.getParameter("tid"));//要评价的老师
		TeacherBean teacher=teacherDao.querById(id);
		String hql="select count(*) from EvaluationBean where teacherid="+teacher.getId();
		int evalnum=evaluateDao.getRowsByTeacherid(hql);
				
		float updScore=teacher.getScore()+totalScore*20/100;//学生评价占20%的比例
		teacher.setScore((teacher.getScore()*evalnum+updScore)/(evalnum+1));//修改分数
		teacher.setEvalsum(evalnum+1);//修改评价人数，增加一人
		teacherDao.updateTea(teacher);
		
		ExpertsBean loginuser=(ExpertsBean)session.getAttribute("loginuser");//登录人员
		
		EvaluationBean evaluate=new EvaluationBean();
		evaluate.setTeacherid(teacher.getId());
		evaluate.setEvalid(loginuser.getId());
		evaluate.setEvaltypeid(loginuser.getTypeid());
		evaluate.setEvalscore(updScore);
		evaluate.setRemark(evalForm.getRemark());
		evaluate.setDatetime(GetTime.getTime());
		evaluateDao.addEvaluation(evaluate);//添加评价信息
		
		//获取评价留言
		List list=evaluateDao.getEvaluationByTid(teacher.getId());
		request.setAttribute("remarklist", list);
		//调用分数显示办法
		String gradepath=ShowGrede.getGradePath(teacher.getScore());
		request.setAttribute("gradepath", gradepath);
		request.setAttribute("teacher", teacher);
		return mapping.findForward("Success");
	}
	//领导评价
	@SuppressWarnings("unchecked")
	public ActionForward leaderEval(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		EvaluateForm evalForm=(EvaluateForm)form;
		float totalScore=evalForm.getNum1()+evalForm.getNum2()+evalForm.getNum3()+evalForm.getNum4()
						+evalForm.getNum5()+evalForm.getNum6()+evalForm.getNum7()+evalForm.getNum8();//获取页面评分的总分
				
		int id=Integer.parseInt(request.getParameter("tid"));//要评价的老师
		TeacherBean teacher=teacherDao.querById(id);
		String hql="select count(*) from EvaluationBean where teacherid="+teacher.getId();
		int evalnum=evaluateDao.getRowsByTeacherid(hql);
				
		float updScore=teacher.getScore()+totalScore*20/100;//学生评价占20%的比例
		teacher.setScore((teacher.getScore()*evalnum+updScore)/(evalnum+1));//修改分数
		teacher.setEvalsum(evalnum+1);//修改评价人数，增加一人
		teacherDao.updateTea(teacher);
		
		LeaderBean loginuser=(LeaderBean)session.getAttribute("loginuser");//登录人员
		
		EvaluationBean evaluate=new EvaluationBean();
		evaluate.setTeacherid(teacher.getId());
		evaluate.setEvalid(loginuser.getId());
		evaluate.setEvaltypeid(loginuser.getTypeid());
		evaluate.setEvalscore(updScore);
		evaluate.setRemark(evalForm.getRemark());
		evaluate.setDatetime(GetTime.getTime());
		evaluateDao.addEvaluation(evaluate);//添加评价信息
		
		//获取评价留言
		List list=evaluateDao.getEvaluationByTid(teacher.getId());
		request.setAttribute("remarklist", list);
		//调用分数显示办法
		String gradepath=ShowGrede.getGradePath(teacher.getScore());
		request.setAttribute("gradepath", gradepath);
		request.setAttribute("teacher", teacher);
		return mapping.findForward("Success");
	}
	//家长评价
	@SuppressWarnings("unchecked")
	public ActionForward parentsEval(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		EvaluateForm evalForm=(EvaluateForm)form;
		float totalScore=evalForm.getNum1()+evalForm.getNum2()+evalForm.getNum3()+evalForm.getNum4()
						+evalForm.getNum5();//获取页面评分的总分
				
		int id=Integer.parseInt(request.getParameter("tid"));//要评价的老师
		TeacherBean teacher=teacherDao.querById(id);
		String hql="select count(*) from EvaluationBean where teacherid="+teacher.getId();
		int evalnum=evaluateDao.getRowsByTeacherid(hql);
				
		float updScore=teacher.getScore()+totalScore*10/100;//学生评价占10%的比例
		teacher.setScore((teacher.getScore()*evalnum+updScore)/(evalnum+1));//修改分数
		teacher.setEvalsum(evalnum+1);//修改评价人数，增加一人
		teacherDao.updateTea(teacher);
		
		ParentsBean loginuser=(ParentsBean)session.getAttribute("loginuser");//登录人员
		
		EvaluationBean evaluate=new EvaluationBean();
		evaluate.setTeacherid(teacher.getId());
		evaluate.setEvalid(loginuser.getId());
		evaluate.setEvaltypeid(loginuser.getTypeid());
		evaluate.setEvalscore(updScore);
		evaluate.setRemark(evalForm.getRemark());
		evaluate.setDatetime(GetTime.getTime());
		evaluateDao.addEvaluation(evaluate);//添加评价信息
		
		//获取评价留言
		List list=evaluateDao.getEvaluationByTid(teacher.getId());
		request.setAttribute("remarklist", list);
		//调用分数显示办法
		String gradepath=ShowGrede.getGradePath(teacher.getScore());
		request.setAttribute("gradepath", gradepath);
		request.setAttribute("teacher", teacher);
		return mapping.findForward("Success");
	}
	//管理界面分页查询
	@SuppressWarnings("unchecked")
	public ActionForward MqueryWithPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int totalRows=evaluateDao.getRows("select count(*) from EvaluationBean as t");
		Pager pager=PagerHelper.getPager(request, totalRows);
		List list=evaluateDao.findWithPage(pager.getPageSize(), pager.getStartRow());
		request.setAttribute("EVALUATION", list);
		request.setAttribute("PAGER", pager);
		return mapping.findForward("MquerySuccess");
	}
	//删除评价信息
	@SuppressWarnings("unchecked")
	public ActionForward DelEvaluation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		int id=Integer.parseInt(request.getParameter("id"));
		evaluateDao.delEvaluation(id);
		int totalRows=evaluateDao.getRows("select count(*) from EvaluationBean as t");
		Pager pager=PagerHelper.getPager(request, totalRows);
		List list=evaluateDao.findWithPage(pager.getPageSize(), pager.getStartRow());
		request.setAttribute("EVALUATION", list);
		request.setAttribute("PAGER", pager);
		return mapping.findForward("MquerySuccess");
		}
}
