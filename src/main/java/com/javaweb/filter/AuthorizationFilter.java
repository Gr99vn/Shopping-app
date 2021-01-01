package com.javaweb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaweb.constant.SystemConstant;
import com.javaweb.model.UserModel;
import com.javaweb.utils.SessionUtil;

public class AuthorizationFilter implements Filter{
	
	private ServletContext context;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();
		System.out.println(this.context.toString());
	}
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		String url = request.getRequestURI();
		if (url.startsWith(request.getContextPath()+"/admin")) {
			UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
			if (userModel != null) {
				if (userModel.getRole().getCode().equals(SystemConstant.adminRole)) {
					chain.doFilter(request, response);
				} else if (userModel.getRole().getCode().equals(SystemConstant.userRole)) {
					response.sendRedirect(request.getContextPath()+"/login?action=login&message=not_permission");
				}
			} else {
				response.sendRedirect(request.getContextPath()+"/login?action=login&message=not_login");
			}
		} else {
			chain.doFilter(request, response); 
		}
		
	}
	@Override
	public void destroy() {

	}
}
