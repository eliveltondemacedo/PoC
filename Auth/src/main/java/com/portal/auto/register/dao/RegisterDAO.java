package com.portal.auto.register.dao;

import com.portal.auto.bean.UserBean;

public interface RegisterDAO {

	/**
	 * @param user UserBean
	 * @return true:sucesso, false: falha
	 */
	public boolean registerUser(UserBean user);
	
	/**
	 * @param user User Name
	 * @return true:User Existente, false: Usuário não existe
	 */
	public boolean checkUser(String user);
	
}
