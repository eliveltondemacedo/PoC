package com.portal.auto.service;

import com.portal.auto.bean.UserBean;

public interface LoginService {

	/**
	 * @param userName User Name
	 * @return UserBean
	 */
	UserBean findByUserName(String userName);
	
	/**
	 * @param userBean User Object
	 */
	boolean updateToken(UserBean userBean,String token, String userType);
	
}
