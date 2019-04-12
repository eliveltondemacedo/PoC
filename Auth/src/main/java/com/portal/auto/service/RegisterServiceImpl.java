package com.portal.auto.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.portal.auto.bean.RegisterBean;
import com.portal.auto.bean.ResponseBean;
import com.portal.auto.bean.UserBean;
import com.portal.auto.bean.UserToken;
import com.portal.auto.constants.CommonConstants;
import com.portal.auto.register.dao.RegisterDAO;
import com.portal.auto.register.validation.UserValidation;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	public RegisterDAO registerDAO;
	
	@Autowired
	public UserValidation userValidation;
	
	@Autowired
	public BCryptPasswordEncoder passwordEncoder;
	
	public ResponseBean registerUser(RegisterBean register) {
		
		ResponseBean reponseBean = new ResponseBean();
		
		String validation = userValidation.validateUser(register);
		if(StringUtils.isNotBlank(validation)){
			reponseBean.setFlag("failure");
			reponseBean.setMessage(validation);
			return reponseBean;
		}
		
		if(registerDAO.checkUser(register.getName())){
			reponseBean.setFlag("failure");
			reponseBean.setMessage("Usuário existente, por favor tente outro nome");
			return reponseBean;
		}
		
		UserBean userBean = new UserBean();
		userBean.setUserName(register.getName());
		userBean.setPassword(passwordEncoder.encode(register.getPassword()));
		userBean.setRole(CommonConstants.ROLE_USER);
		
		UserToken tokenWeb = new UserToken();
		tokenWeb.setUserType(CommonConstants.WEB_USER);
		tokenWeb.setUser(userBean);
		
		UserToken tokenAn = new UserToken();
		tokenAn.setUserType(CommonConstants.ANDRIOD_USER);
		tokenAn.setUser(userBean);
		
		userBean.getUserToken().add(tokenWeb);
		userBean.getUserToken().add(tokenAn);
		
		boolean creationFlag = registerDAO.registerUser(userBean);
		if(creationFlag){
			reponseBean.setFlag("sucesso");
			reponseBean.setMessage("Usuário registrado);
			return reponseBean;
		} else {
			reponseBean.setFlag("Falha");
			reponseBean.setMessage("Usuário registrado com falha");
			return reponseBean;
		}
	}

}
