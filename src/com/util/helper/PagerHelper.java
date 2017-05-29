package com.util.helper;

import javax.servlet.http.HttpServletRequest;

public class PagerHelper {
	public static Pager getPager(HttpServletRequest httpServletRequest,int totalRows){
		//定义Pager对象，用于传到页面
		Pager pager=new Pager(totalRows);
		//从request对象中获取当前页号
		String currentPage=httpServletRequest.getParameter("currentPage");
		//如果当前页号为空，表示首次查询该页
		//如果不为空，则刷新Pager对象，输入当前页号等信息
		if(currentPage!=null){
			pager.refresh(Integer.parseInt(currentPage));
		}
		//获取当前执行的方法，首页，前一页，后一页，尾页
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
