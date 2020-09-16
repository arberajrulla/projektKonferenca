package com.ikubinfo.konferenca.managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.ikubinfo.konferenca.dto.UserDto;

@ManagedBean(name = "loggedUserBean")
@SessionScoped
public class LoggedUserBean {

	UserDto loggedUser;

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

