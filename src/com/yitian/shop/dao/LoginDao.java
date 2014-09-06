/**
 *
 * @(#) LoginDao.java
 * @Package com.yitian.shop.dao
 * 
 * Copyright © Longshine Corporation. All rights reserved.
 *
 */

/**
 * 
 */
package com.yitian.shop.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *  类描述：
 * 
 *  @author:  JayLee
 *  @version  1.0
 *
 *  History:  2013-11-23 下午08:39:30   JayLee   Created.
 *           
 */
@Repository
public class LoginDao implements ILoginDao {
   @Autowired
   private JdbcTemplate jdbcTemplate;
   private static Logger logger = Logger.getLogger(LoginDao.class);
   public void test(){
	    if(logger.isDebugEnabled()){
	    	logger.debug("【===============dao层被调用===========】");
	    }
   }
   
   public String getPassWord(String loginName){
	    String password = "";
	    String sql = "select password from shopping.user where username = ? ";
	    List<String> list = jdbcTemplate.queryForList(sql, new Object[]{loginName}, String.class);
	    if(list.size()>0){
	      password = list.get(0);
	    }
	    return password;
   }
}
