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
import com.ikubinfo.konferenca.dto.UserDto;
import com.ikubinfo.konferenca.service.UserService;
import com.ikubinfo.konferenca.utils.HashSaltedPassword;

@ManagedBean(name = "authentikimBean")
@RequestScoped
public class AuthentikimBean {

	@ManagedProperty(value = "#{userService}")
	UserService uService;

	@ManagedProperty(value = "#{loggedUserBean}")
	LoggedUserBean loggedUserBean;

	private static Logger log = Logger.getLogger(AuthentikimBean.class);
	
	@NotEmpty(message = "Username nuk mund te jete bosh!")
	@Size(min=6, max=20, message = "Username duhet te kete nga 6 deri 20 karaktere!")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Username duhet te permbaje vetem shkronja dhe numra!")
	private String username;
	
	@NotEmpty(message = "Fjalekalimi nuk mund te jete bosh!")
	@Size(min=6, max=20, message = "Fjalekalimi duhet te kete nga 6 deri 20 karaktere!")
	private String password;
	
	
	private boolean credentialsKey = false;
	
	public boolean isCredentialsKey() {
		return credentialsKey;
	}
	public void setCredentialsKey(boolean credentialsKey) {
		this.credentialsKey = credentialsKey;
	}
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
	public LoggedUserBean getLoggedUserBean() {
		return loggedUserBean;
	}
	public void setLoggedUserBean(LoggedUserBean loggedUserBean) {
		this.loggedUserBean = loggedUserBean;
	}
	
	public String authentikim() {
		UserDto userDto;
		userDto = uService.getUserForLoggin(username);
		if(userDto!=null) {
			HashSaltedPassword hashCheck = new HashSaltedPassword();
			
				if(userDto.getPassword().equals(hashCheck.hashGenerate(password, userDto.getSalt()))) {
					loggedUserBean.setLoggedUser(userDto);
					credentialsKey = false;
					log.info("Login successful");
					switch (userDto.getKategoria()) {
					case "admin":
						return ("admin/faqja1.xhtml?faces-redirect=true");
					case "autor":
						return ("autor_res/faqja1.xhtml?faces-redirect=true");						
					case "shqyrtues":
						return ("shqyrtues_res/faqja1.xhtml?faces-redirect=true");
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

