package com.ssp.share.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssp.share.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request .getSession();
		String contextPath = session.getServletContext().getContextPath();
		String[] requireAuthPages = new String[]{
				//存放需要登录才能访问的路径
				"personalHomepage"
		};
		String uri = request.getRequestURI();
		uri = StringUtils.remove(uri, contextPath+"/");
		String page = uri;
		if(begingWith(page, requireAuthPages)){
			Subject subject = SecurityUtils.getSubject();
			if(!subject.isAuthenticated()) {
				response.sendRedirect("login");
				return false;
			}
		}
		return true;
	}

	private boolean begingWith(String page, String[] requiredAuthPages) {
		boolean result = false;
		for (String requiredAuthPage : requiredAuthPages) {
			if(StringUtils.startsWith(page, requiredAuthPage)) {
				result = true;
				break;
			}
		}
		return result;
	}
}
