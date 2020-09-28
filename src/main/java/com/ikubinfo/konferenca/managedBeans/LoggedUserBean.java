package com.ikubinfo.konferenca.managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;

import com.ikubinfo.konferenca.dto.UserDto;
import com.ikubinfo.konferenca.service.UserService;

@ManagedBean(name = "loggedUserBean")
@SessionScoped
public class LoggedUserBean {

	private static Logger log = Logger.getLogger(LoggedUserBean.class);	
	
	@ManagedProperty (value = "#{userService}")
	UserService userService;

	
	private UserDto loggedUser;

	public UserDto getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(UserDto loggedUser) {
		this.loggedUser = loggedUser;
	}
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void logout() {
		this.loggedUser = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        //return "/home.xhtml?faces-redirect=true";
	}
	
    public String updateLoggedUserDetails() {
		log.info("Preparing to update Logged user details");
		userService.updateUser(loggedUser);
		log.info("Logged user Updated SUCCESSFULLY!");
		return("faqja1.xhtml?faces-redirect=true");
    }
	
	
	public String check() {
		if(this.loggedUser != null) {
			return null;
		}else {
			return("/login.xhtml?faces-redirect=true");
		}
		
	}
	
}

