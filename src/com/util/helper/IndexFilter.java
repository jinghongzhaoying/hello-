package com.util.helper;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class IndexFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)arg0;
		HttpServletResponse response=(HttpServletResponse)arg1;
		HttpSession session=request.getSession();
		//前台登陆界面过滤器
		if(session.getAttribute("TEACHERS")==null)
		{
			request.getRequestDispatcher("/queryTeachers.do?method=queryIndex").forward(request, response);
		}else {
			arg2.doFilter(arg0, arg1);
		}

	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

}
