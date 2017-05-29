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

	//ѧ�����۴���
	@SuppressWarnings("unchecked")
	public ActionForward studentEval(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		EvaluateForm evalForm=(EvaluateForm)form;
		float totalScore=evalForm.getNum1()+evalForm.getNum2()+evalForm.getNum3()+evalForm.getNum4()
						+evalForm.getNum5()+evalForm.getNum6()+evalForm.getNum7()+evalForm.getNum8()
						+evalForm.getNum9()+evalForm.getNum10();//��ȡҳ�����ֵ��ܷ�
				
		int id=Integer.parseInt(request.getParameter("tid"));//Ҫ���۵���ʦ
		TeacherBean teacher=teacherDao.querById(id);
		String hql="select count(*) from EvaluationBean where teacherid="+teacher.getId();
		int evalnum=evaluateDao.getRowsByTeacherid(hql);
				
		float updScore=teacher.getScore()+totalScore*30/100;//ѧ������ռ30%�ı���
		teacher.setScore((teacher.getScore()*evalnum+updScore)/(evalnum+1));//�޸ķ���
		teacher.setEvalsum(evalnum+1);//�޸���������������һ��
		teacherDao.updateTea(teacher);
		
		StudentBean loginuser=(StudentBean)session.getAttribute("loginuser");//��¼��Ա
		
		EvaluationBean evaluate=new EvaluationBean();
		evaluate.setTeacherid(teacher.getId());
		evaluate.setEvalid(loginuser.getId());
		evaluate.setEvaltypeid(loginuser.getTypeid());
		evaluate.setEvalscore(updScore);
		evaluate.setRemark(evalForm.getRemark());
		evaluate.setDatetime(GetTime.getTime());
		evaluateDao.addEvaluation(evaluate);//���������Ϣ
		//��ȡ��������
		List<Object> list=evaluateDao.getEvaluationByTid(teacher.getId());
		request.setAttribute("remarklist", list);
		//���÷�����ʾ�취
		String gradepath=ShowGrede.getGradePath(teacher.getScore());
		request.setAttribute("gradepath", gradepath);
		request.setAttribute("teacher", teacher);
		return mapping.findForward("Success");
	}
	//ͬ������
	@SuppressWarnings("unchecked")
	public ActionForward teacherEval(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		EvaluateForm evalForm=(EvaluateForm)form;
		float totalScore=evalForm.getNum1()+evalForm.getNum2()+evalForm.getNum3()+evalForm.getNum4()
						+evalForm.getNum5()+evalForm.getNum6()+evalForm.getNum7();//��ȡҳ�����ֵ��ܷ�
				
		int id=Integer.parseInt(request.getParameter("tid"));//Ҫ���۵���ʦ
		TeacherBean teacher=teacherDao.querById(id);
		String hql="select count(*) from EvaluationBean where teacherid="+teacher.getId();
		int evalnum=evaluateDao.getRowsByTeacherid(hql);
				
		float updScore=teacher.getScore()+totalScore*15/100;//ѧ������ռ15%�ı���
		teacher.setScore((teacher.getScore()*evalnum+updScore)/(evalnum+1));//�޸ķ���
		teacher.setEvalsum(evalnum+1);//�޸���������������һ��
		teacherDao.updateTea(teacher);
		
		TeacherBean loginuser=(TeacherBean)session.getAttribute("loginuser");//��¼��Ա
		
		EvaluationBean evaluate=new EvaluationBean();
		evaluate.setTeacherid(teacher.getId());
		evaluate.setEvalid(loginuser.getId());
		evaluate.setEvaltypeid(loginuser.getTypeid());
		evaluate.setEvalscore(updScore);
		evaluate.setRemark(evalForm.getRemark());
		evaluate.setDatetime(GetTime.getTime());
		evaluateDao.addEvaluation(evaluate);//���������Ϣ
		
		//��ȡ��������
		List list=evaluateDao.getEvaluationByTid(teacher.getId());
		request.setAttribute("remarklist", list);
		//���÷�����ʾ�취
		String gradepath=ShowGrede.getGradePath(teacher.getScore());
		request.setAttribute("gradepath", gradepath);
		request.setAttribute("teacher", teacher);
		return mapping.findForward("Success");
	}
	//ר������
	@SuppressWarnings("unchecked")
	public ActionForward expertsEval(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		EvaluateForm evalForm=(EvaluateForm)form;
		float totalScore=evalForm.getNum1()+evalForm.getNum2()+evalForm.getNum3()+evalForm.getNum4()
						+evalForm.getNum5();//��ȡҳ�����ֵ��ܷ�
				
		int id=Integer.parseInt(request.getParameter("tid"));//Ҫ���۵���ʦ
		TeacherBean teacher=teacherDao.querById(id);
		String hql="select count(*) from EvaluationBean where teacherid="+teacher.getId();
		int evalnum=evaluateDao.getRowsByTeacherid(hql);
				
		float updScore=teacher.getScore()+totalScore*20/100;//ѧ������ռ20%�ı���
		teacher.setScore((teacher.getScore()*evalnum+updScore)/(evalnum+1));//�޸ķ���
		teacher.setEvalsum(evalnum+1);//�޸���������������һ��
		teacherDao.updateTea(teacher);
		
		ExpertsBean loginuser=(ExpertsBean)session.getAttribute("loginuser");//��¼��Ա
		
		EvaluationBean evaluate=new EvaluationBean();
		evaluate.setTeacherid(teacher.getId());
		evaluate.setEvalid(loginuser.getId());
		evaluate.setEvaltypeid(loginuser.getTypeid());
		evaluate.setEvalscore(updScore);
		evaluate.setRemark(evalForm.getRemark());
		evaluate.setDatetime(GetTime.getTime());
		evaluateDao.addEvaluation(evaluate);//���������Ϣ
		
		//��ȡ��������
		List list=evaluateDao.getEvaluationByTid(teacher.getId());
		request.setAttribute("remarklist", list);
		//���÷�����ʾ�취
		String gradepath=ShowGrede.getGradePath(teacher.getScore());
		request.setAttribute("gradepath", gradepath);
		request.setAttribute("teacher", teacher);
		return mapping.findForward("Success");
	}
	//�쵼����
	@SuppressWarnings("unchecked")
	public ActionForward leaderEval(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		EvaluateForm evalForm=(EvaluateForm)form;
		float totalScore=evalForm.getNum1()+evalForm.getNum2()+evalForm.getNum3()+evalForm.getNum4()
						+evalForm.getNum5()+evalForm.getNum6()+evalForm.getNum7()+evalForm.getNum8();//��ȡҳ�����ֵ��ܷ�
				
		int id=Integer.parseInt(request.getParameter("tid"));//Ҫ���۵���ʦ
		TeacherBean teacher=teacherDao.querById(id);
		String hql="select count(*) from EvaluationBean where teacherid="+teacher.getId();
		int evalnum=evaluateDao.getRowsByTeacherid(hql);
				
		float updScore=teacher.getScore()+totalScore*20/100;//ѧ������ռ20%�ı���
		teacher.setScore((teacher.getScore()*evalnum+updScore)/(evalnum+1));//�޸ķ���
		teacher.setEvalsum(evalnum+1);//�޸���������������һ��
		teacherDao.updateTea(teacher);
		
		LeaderBean loginuser=(LeaderBean)session.getAttribute("loginuser");//��¼��Ա
		
		EvaluationBean evaluate=new EvaluationBean();
		evaluate.setTeacherid(teacher.getId());
		evaluate.setEvalid(loginuser.getId());
		evaluate.setEvaltypeid(loginuser.getTypeid());
		evaluate.setEvalscore(updScore);
		evaluate.setRemark(evalForm.getRemark());
		evaluate.setDatetime(GetTime.getTime());
		evaluateDao.addEvaluation(evaluate);//���������Ϣ
		
		//��ȡ��������
		List list=evaluateDao.getEvaluationByTid(teacher.getId());
		request.setAttribute("remarklist", list);
		//���÷�����ʾ�취
		String gradepath=ShowGrede.getGradePath(teacher.getScore());
		request.setAttribute("gradepath", gradepath);
		request.setAttribute("teacher", teacher);
		return mapping.findForward("Success");
	}
	//�ҳ�����
	@SuppressWarnings("unchecked")
	public ActionForward parentsEval(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		EvaluateForm evalForm=(EvaluateForm)form;
		float totalScore=evalForm.getNum1()+evalForm.getNum2()+evalForm.getNum3()+evalForm.getNum4()
						+evalForm.getNum5();//��ȡҳ�����ֵ��ܷ�
				
		int id=Integer.parseInt(request.getParameter("tid"));//Ҫ���۵���ʦ
		TeacherBean teacher=teacherDao.querById(id);
		String hql="select count(*) from EvaluationBean where teacherid="+teacher.getId();
		int evalnum=evaluateDao.getRowsByTeacherid(hql);
				
		float updScore=teacher.getScore()+totalScore*10/100;//ѧ������ռ10%�ı���
		teacher.setScore((teacher.getScore()*evalnum+updScore)/(evalnum+1));//�޸ķ���
		teacher.setEvalsum(evalnum+1);//�޸���������������һ��
		teacherDao.updateTea(teacher);
		
		ParentsBean loginuser=(ParentsBean)session.getAttribute("loginuser");//��¼��Ա
		
		EvaluationBean evaluate=new EvaluationBean();
		evaluate.setTeacherid(teacher.getId());
		evaluate.setEvalid(loginuser.getId());
		evaluate.setEvaltypeid(loginuser.getTypeid());
		evaluate.setEvalscore(updScore);
		evaluate.setRemark(evalForm.getRemark());
		evaluate.setDatetime(GetTime.getTime());
		evaluateDao.addEvaluation(evaluate);//���������Ϣ
		
		//��ȡ��������
		List list=evaluateDao.getEvaluationByTid(teacher.getId());
		request.setAttribute("remarklist", list);
		//���÷�����ʾ�취
		String gradepath=ShowGrede.getGradePath(teacher.getScore());
		request.setAttribute("gradepath", gradepath);
		request.setAttribute("teacher", teacher);
		return mapping.findForward("Success");
	}
	//��������ҳ��ѯ
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
	//ɾ��������Ϣ
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
