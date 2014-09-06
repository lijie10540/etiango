package com.yitian.fw;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebcHelper {
	private final static String LS_PAGE_CONTEXT="YT_PAGE_CONTEXT";
	private WebcHelper() {

	}

	public static HttpServletRequest getRequest() {
		return ControlContext.getRequest();
	}

	public static HttpServletResponse getResponse() {
		return ControlContext.getResponse();
	}
	
	public static void setRequest(HttpServletRequest request){
		ControlContext.setRequest(request);
	}
	
	public static void setResponse(HttpServletResponse response){
		ControlContext.setResponse(response);
	}
	
	public static void pojo2Context(Object pojo) {
		getRequest().setAttribute(LS_PAGE_CONTEXT, pojo);
	}
	
	public static String[] getRequestParamValues(String key){
		return getRequest().getParameterValues(key);
	}
	
	public static String getWebContext(){
		return getRequest().getContextPath();
	}
}
	
	
