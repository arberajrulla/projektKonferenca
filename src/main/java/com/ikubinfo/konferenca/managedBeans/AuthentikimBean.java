package com.ikubinfo.konferenca.managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.NotEmpty;
import org.jasypt.util.password.StrongPasswordEncryptor;

import com.ikubinfo.konferenca.dao.impl.UserDaoImpl;
import com.ikubinfo.konferenca.dto.AutorDto;
import com.ikubinfo.konferenca.dto.ShqyrtuesDto;
import com.ikubinfo.konferenca.dto.UserDto;
import com.ikubinfo.konferenca.service.AutorService;
import com.ikubinfo.konferenca.service.ShqyrtuesService;
import com.ikubinfo.konferenca.service.UserService;
import com.ikubinfo.konferenca.utils.HashSaltedPassword;
import com.ikubinfo.konferenca.utils.UtilMessages;

@ManagedBean(name = "authentikimBean")
@RequestScoped
public class AuthentikimBean {
	
	private static Logger log = Logger.getLogger(AuthentikimBean.class);

	@ManagedProperty(value = "#{userService}")
	UserService uService;

	@ManagedProperty(value = "#{loggedUserBean}")
	LoggedUserBean loggedUserBean;
	
	@ManagedProperty(value = "#{autorService}")
	AutorService autorService;
	
	@ManagedProperty(value = "#{shqyrtuesService}")
	ShqyrtuesService shqyrtuesService;

	private UserDto loggingUser = new UserDto();
	private boolean credentialsKey = false;
	
	public UserDto getLoggingUser() {
		return loggingUser;
	}
	public void setLoggingUser(UserDto loggingUser) {
		this.loggingUser = loggingUser;
	}
	public boolean isCredentialsKey() {
		return credentialsKey;
	}
	public void setCredentialsKey(boolean credentialsKey) {
		this.credentialsKey = credentialsKey;
	}	
	public UserService getuService() {
		return uService;
	}
	public void setuService(UserService uService) {
		this.uService = uService;
	}
	public LoggedUserBean getLoggedUserBean() {
		return loggedUserBean;
	}
	public void setLoggedUserBean(LoggedUserBean loggedUserBean) {
		this.loggedUserBean = loggedUserBean;
	}
	public AutorService getAutorService() {
		return autorService;
	}
	public void setAutorService(AutorService autorService) {
		this.autorService = autorService;
	}
	public ShqyrtuesService getShqyrtuesService() {
		return shqyrtuesService;
	}
	public void setShqyrtuesService(ShqyrtuesService shqyrtuesService) {
		this.shqyrtuesService = shqyrtuesService;
	}
	
	
	public String authentikim() {
		UserDto userDto;
		userDto = uService.getUserForLoggin(loggingUser.getUsername());
		if(userDto!=null) {
			HashSaltedPassword hashCheck = new HashSaltedPassword();
			
				if(userDto.getPassword().equals(hashCheck.hashGenerate(loggingUser.getPassword(), userDto.getSalt()))) {
					
					credentialsKey = false;
					log.info("Login successful");
					switch (userDto.getKategoria()) {
					case "admin":
						loggedUserBean.setLoggedUser(userDto);
						return ("admin/faqja1.xhtml?faces-redirect=true");
					case "autor":
						AutorDto autorDto = autorService.getAutor(userDto.getEmail());
						if(autorDto==null && userDto.getRegisterStatus()==1) {
							throw new RuntimeException(UtilMessages.bundle.getString("EXCEPTION_USER_EXISTS_WITHOUT_CATEGORY"));
						}
						
						if (userDto.getRegisterStatus()==0) {
							loggedUserBean.setTemporaryLoggedUser(userDto);
							return ("autor_res/perfundimRegjistrimi.xhtml?faces-redirect=true");
						}else {
							loggedUserBean.setLoggedUser(userDto);
							loggedUserBean.setLoggedAutor(autorDto);
							return ("autor_res/faqja1.xhtml?faces-redirect=true");
						}
						
						
					case "shqyrtues":
						ShqyrtuesDto shqyrtuesDto = shqyrtuesService.getShqyrtues(userDto.getEmail());
						if(shqyrtuesDto==null && userDto.getRegisterStatus()==1) {
							throw new RuntimeException(UtilMessages.bundle.getString("EXCEPTION_USER_EXISTS_WITHOUT_CATEGORY"));
						}
						
						if (userDto.getRegisterStatus()==0) {
							loggedUserBean.setTemporaryLoggedUser(userDto);
							return ("shqyrtues_res/perfundimRegjistrimi.xhtml?faces-redirect=true");
						}else {
							loggedUserBean.setLoggedUser(userDto);
							loggedUserBean.setLoggedShqyrtues(shqyrtuesDto);
							return ("shqyrtues_res/faqja1.xhtml?faces-redirect=true");
						}
					default:
						return ("login.xhtml?faces-redirect=true");
					}
				}else {
					log.error("Incorrect password");
					credentialsKey = true;
				}
		} else {
			log.error("Incorrect username");
				credentialsKey = true;
		}
		return null;
	}
	
	
	public String regjistrim() {
		return("regjistrim.xhtml?faces-redirect=true");
	}
	
	
	public String dil() {
		loggedUserBean.logout();
		return("/login.xhtml?faces-redirect=true");
	}
}

