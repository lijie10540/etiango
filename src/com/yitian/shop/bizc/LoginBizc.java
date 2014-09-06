/**
 *
 * @(#) LoginBizc.java
 * @Package com.yitian.shop.bizc
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

/**
 * 
 */
package com.yitian.shop.bizc;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yitian.base.utils.DesUtils;
import com.yitian.shop.dao.ILoginDao;

/**
 *  类描述：
 * 
 *  @author:  JayLee
 *  @version  1.0
 *
 *  History:  2013-11-23 下午08:36:51   JayLee   Created.
 *           
 */
/**
 * @author JayLee
 * 
 */
@Service
public class LoginBizc implements ILoginBizc {
	@Autowired
	private ILoginDao loginDao;
	 private static Logger logger = Logger.getLogger(LoginBizc.class);
	   public void test(){
		    if(logger.isDebugEnabled()){
		    	logger.debug("【===============bizc层被调用===========】");
		    }
		    loginDao.test();
	   }
	   
	   public boolean checkLogin(String username,String password){
		    String dbPassword = loginDao.getPassWord(username);
		    if(password.equals(DesUtils.decrypt(dbPassword))){
		     return true;
		    }else{
		     return false;
		    }
	   }

}
