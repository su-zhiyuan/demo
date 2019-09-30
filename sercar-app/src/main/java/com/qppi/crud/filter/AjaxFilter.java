package com.qppi.crud.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjaxFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
		FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hrequest = (HttpServletRequest)request;
		HttpServletResponse hresponse = (HttpServletResponse)response;
	    hresponse.addHeader("Access-Control-Allow-Origin", "*");
	    hresponse.addHeader("Access-Control-Allow-Headers", "Content-Type");
	    hrequest.setCharacterEncoding("utf-8");
	    hresponse.setCharacterEncoding("utf-8");
	    chain.doFilter(request, hresponse);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}