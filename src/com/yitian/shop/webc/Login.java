/**
 *
 * @(#) Login.java
 * @Package com.yitian.shop.webc
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

/**
 * 
 */
package com.yitian.shop.webc;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yitian.base.utils.DesUtils;
import com.yitian.fw.WebcHelper;
import com.yitian.shop.bizc.ILoginBizc;

/**
 *  类描述：
 * 
 *  @author:  JayLee
 *  @version  1.0
 *
 *  History:  2013-11-23 下午06:44:37   JayLee   Created.
 *           
 */
@Controller
@RequestMapping("/main")
public class Login {
	private static Logger logger = Logger.getLogger(Login.class);
	@Autowired
	private ILoginBizc loginBizc;
	@RequestMapping("/login")
	public ModelAndView login() {  
		   HttpServletRequest request = WebcHelper.getRequest();
		   String loginName=ServletRequestUtils.getStringParameter(request, "loginName", "");
		   String passWord = ServletRequestUtils.getStringParameter(request, "passWord", "");
//		   logger.info(DesUtils.encrypt(passWord));
//		   if(loginBizc.checkLogin(loginName,passWord)){
//			   logger.info("登陆成功！！！");
//		   }else{
//			   logger.info("登陆失败！！！");
//		   }
		   ModelAndView mo = new ModelAndView();
		   mo.addObject("version", "1.0");
		   mo.addObject("sysname", "yitianyitian");
		   mo.setViewName("face/map");
		   return mo;  
	    }  
	 
}
