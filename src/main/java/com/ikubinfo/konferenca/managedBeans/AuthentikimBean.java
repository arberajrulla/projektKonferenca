package com.ikubinfo.konferenca.managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;
import org.jasypt.util.password.StrongPasswordEncryptor;

import com.ikubinfo.konferenca.dao.impl.UserDaoImpl;
import com.ikubinfo.konferenca.dto.UserDto;
import com.ikubinfo.konferenca.service.UserService;

@ManagedBean(name = "authentikimBean")
@RequestScoped
public class AuthentikimBean {

	@ManagedProperty(value = "#{userService}")
	UserService uService;

	@ManagedProperty(value = "#{userBean}")
	UserBean userBean;

	
	private static Logger log = Logger.getLogger(AuthentikimBean.class);
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String email) {
		this.username = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
	public UserService getuService() {
		return uService;
	}
	public void setuService(UserService uService) {
		this.uService = uService;
	}
	public UserBean getUserBean() {
		return userBean;
	}
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	
	public String authentikim() {
		
		UserDto userDto = new UserDto();
		userDto = uService.getUserForLoggin(username);
		System.out.println(username);
		if(userDto!=null) {
			
			System.out.println("Autentikimi password: " + userDto.getPassword());
			if(this.password.equals(userDto.getPassword())) {	
				userBean.setUserDto(userDto);
				System.out.println("Login successful");
				log.info("Login successful from log");
				return ("admin/kryesore.xhtml?faces-redirect=true");
			}else {
				System.out.println("Incorrect password");
			}
		} else {
				System.out.println("Incorrect username");
		}
		return null;
	}
	
	public String dil() {
		userBean.logOut();
		return("login.xhtml");
	}
	
			
}

