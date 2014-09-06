package com.yitian.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yitian.fw.WebcHelper;

public class CommonConfigFilter implements Filter{

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletResponse httpServletReponse=(HttpServletResponse)response;
		
		httpServletReponse.setHeader("progma","no-cache");   
		httpServletReponse.setHeader("Cache-Control","no-cache");   
		httpServletReponse.setDateHeader("Expires",0);
		
		WebcHelper.setRequest((HttpServletRequest)request);
		
		WebcHelper.setResponse((HttpServletResponse)response);
		
		chain.doFilter(request, httpServletReponse);
	}

	public void destroy() {
		
	}

}
