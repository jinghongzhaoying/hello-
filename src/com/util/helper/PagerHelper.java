package com.util.helper;

import javax.servlet.http.HttpServletRequest;

public class PagerHelper {
	public static Pager getPager(HttpServletRequest httpServletRequest,int totalRows){
		//����Pager�������ڴ���ҳ��
		Pager pager=new Pager(totalRows);
		//��request�����л�ȡ��ǰҳ��
		String currentPage=httpServletRequest.getParameter("currentPage");
		//�����ǰҳ��Ϊ�գ���ʾ�״β�ѯ��ҳ
		//�����Ϊ�գ���ˢ��Pager�������뵱ǰҳ�ŵ���Ϣ
		if(currentPage!=null){
			pager.refresh(Integer.parseInt(currentPage));
		}
		//��ȡ��ǰִ�еķ�������ҳ��ǰһҳ����һҳ��βҳ
		String pageMethod=httpServletRequest.getParameter("pageMethod");
		if(pageMethod!=null){
			if(pageMethod.equals("first")){
				pager.first();
			}else if(pageMethod.equals("previous")){
				pager.previous();
			}else if(pageMethod.equals("next")){
				pager.next();
			}else if(pageMethod.equals("last")){
				pager.last();
			}
		}
		return pager;
	}

}
