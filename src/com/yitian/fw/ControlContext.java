package com.yitian.fw;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControlContext {
	@SuppressWarnings("unchecked")
	private static ThreadLocal controlContextThreadLocal=new ThreadLocal();
	private ControlContext(){}
	@SuppressWarnings("unchecked")
	private static Map<String, Object> getControllMap(){
		Map<String, Object> controllContextMap=(Map<String, Object>)controlContextThreadLocal.get();
		if(controllContextMap==null){
			controllContextMap=new HashMap<String, Object>();
			controlContextThreadLocal.set(controllContextMap);
		}
		return controllContextMap;
	}
	
	private static void setObject(String key,Object object){
		getControllMap().put(key, object);
	}
	
	private static Object getObject(String key){
		return getControllMap().get(key);
	}
	
	public static void setRequest(HttpServletRequest request){
		setObject("request",request);
	}
	
	public static HttpServletRequest getRequest(){
		return (HttpServletRequest)getObject("request");
	}
	
	public static void setResponse(HttpServletResponse response){
		setObject("response",response);
	}
	
	public static HttpServletResponse getResponse(){
		return (HttpServletResponse)getObject("response");
	}
}
