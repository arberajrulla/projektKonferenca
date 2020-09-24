package com.ikubinfo.konferenca.managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import com.ikubinfo.konferenca.dto.UserDto;

@ManagedBean(name = "loggedUserBean")
@SessionScoped
public class LoggedUserBean {

	private static Logger log = Logger.getLogger(LoggedUserBean.class);
	
	private UserDto loggedUser;

	public UserDto getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(UserDto loggedUser) {
		this.loggedUser = loggedUser;
	}
	
	public void logout() {
		this.loggedUser = null;
	}
	
}

