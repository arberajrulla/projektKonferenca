package com.ikubinfo.konferenca.managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;

import com.ikubinfo.konferenca.dto.AutorDto;
import com.ikubinfo.konferenca.dto.ShqyrtuesDto;
import com.ikubinfo.konferenca.dto.UserDto;
import com.ikubinfo.konferenca.service.AutorService;
import com.ikubinfo.konferenca.service.ShqyrtuesService;
import com.ikubinfo.konferenca.service.UserService;
import com.ikubinfo.konferenca.utils.MailUtil;
import com.ikubinfo.konferenca.utils.UtilMessages;


@ManagedBean(name = "passwordRestoreBean")
@SessionScoped
public class PasswordRestoreBean {

	private static Logger log = Logger.getLogger(PasswordRestoreBean.class);	

	@ManagedProperty(value = "#{userService}")
	UserService userService;

	@ManagedProperty(value = "#{loggedUserBean}")
	LoggedUserBean loggedUserBean;
	
	@ManagedProperty (value = "#{autorService}")
	AutorService autorService;

	@ManagedProperty (value = "#{shqyrtuesService}")
	ShqyrtuesService shqyrtuesService;
	
	private UserDto passwordResetUser = new UserDto();

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
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public LoggedUserBean getLoggedUserBean() {
		return loggedUserBean;
	}
	public void setLoggedUserBean(LoggedUserBean loggedUserBean) {
		this.loggedUserBean = loggedUserBean;
	}
	public UserDto getPasswordResetUser() {
		return passwordResetUser;
	}
	public void setPasswordResetUser(UserDto passwordResetUser) {
		this.passwordResetUser = passwordResetUser;
	}
	
	
	public void sendEmail() {
		UserDto userDto = userService.getSingleUser(passwordResetUser.getEmail());
		if (userDto!=null) {
			if(userDto.getEmail().equals(passwordResetUser.getEmail())) {
				passwordResetUser.setKodi(MailUtil.codeGenerate());
				MailUtil.sendResetMail(passwordResetUser.getEmail(), passwordResetUser.getKodi());
			}
		} else {
			UtilMessages.addMessageError(null, 
					UtilMessages.bundle.getString("USER_NOT_EXISTS_EMAIL"));
		}

	}
	
	public String verifikoKodin() {
		if(passwordResetUser.getKodi().equals(passwordResetUser.getEnteredKodi())) {
			UserDto userDto = userService.getSingleUser(passwordResetUser.getEmail());
			loggedUserBean.setLoggedUser(userDto);
			passwordResetUser.setErrorKey(false);
			return "/harrimFjalekalimiConfirm.xhtml?faces-redirect=true";
		}else {
			passwordResetUser.setErrorKey(true);
			return null;
		}
	}
	
	public String setNewPassword() {
		if(passwordResetUser.getNewPassword().equals(passwordResetUser.getNewPasswordConfirm())) {
			UserDto u = new UserDto();
			u.setPassword(passwordResetUser.getNewPasswordConfirm());
			
			if(userService.recoverUserPassword(u)) {
				return loginAfterPaswordReset();
			}
		}else {
			UtilMessages.addMessageError(null, 
					UtilMessages.bundle.getString("PASSWORDS_DO_NOT_MATCH"));
		}
		return null;
	}
	
	
	public String loginAfterPaswordReset() {

		UserDto userDto = userService.getSingleUser(passwordResetUser.getEmail());
		switch (userDto.getKategoria()) {
		case "admin":
			loggedUserBean.setLoggedUser(userDto);
			return ("admin/faqja1.xhtml?faces-redirect=true");
		case "autor":
			AutorDto autorDto = autorService.getAutor(userDto.getEmail());
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
			if (userDto.getRegisterStatus()==0) {
				loggedUserBean.setTemporaryLoggedUser(userDto);
				return ("shqyrtues_res/perfundimRegjistrimi.xhtml?faces-redirect=true");
			}else {
				loggedUserBean.setLoggedUser(userDto);
				loggedUserBean.setLoggedShqyrtues(shqyrtuesDto);
				return ("shqyrtues_res/faqja1.xhtml?faces-redirect=true");
			}	

		default:
			UtilMessages.addMessageError(null, 
					UtilMessages.bundle.getString("ERROR_LOGIN"));
			return null;
		}
		
	}
	
	
	
}
