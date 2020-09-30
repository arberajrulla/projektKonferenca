package com.ikubinfo.konferenca.managedBeans;

import java.util.EmptyStackException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.NotEmpty;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;

import com.ikubinfo.konferenca.dto.UserDto;
import com.ikubinfo.konferenca.service.UserService;
import com.ikubinfo.konferenca.utils.HashSaltedPassword;
import com.ikubinfo.konferenca.utils.UtilMessages;

@ManagedBean(name = "loggedUserBean")
@SessionScoped
public class LoggedUserBean {

	private static Logger log = Logger.getLogger(LoggedUserBean.class);	
	
	@ManagedProperty (value = "#{userService}")
	UserService userService;

	private UserDto loggedUser;
	
	@NotEmpty(message = "Fjalekalimi nuk mund te jete bosh!")
	@Size(min=6, max=20, message = "Fjalekalimi duhet te kete nga 6 deri 20 karaktere!")
	private String oldPassword;

	@NotEmpty(message = "Fjalekalimi nuk mund te jete bosh!")
	@Size(min=6, max=20, message = "Fjalekalimi duhet te kete nga 6 deri 20 karaktere!")
	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[@!#$%^&+=]).{6,}$", message = "Fjalekalimi duhet te permbaje te pakten nje numer dhe nje karakter special (@,!,#,$,%,^,&,+,=)!")
	private String newPassword;
	
	@NotEmpty(message = "Fjalekalimi nuk mund te jete bosh!")
	@Size(min=6, max=20, message = "Fjalekalimi duhet te kete nga 6 deri 20 karaktere!")
	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[@!#$%^&+=]).{6,}$", message = "Fjalekalimi duhet te permbaje te pakten nje numer dhe nje karakter special (@,!,#,$,%,^,&,+,=)!")
	private String newPasswordConfirm;

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
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getNewPasswordConfirm() {
		return newPasswordConfirm;
	}
	public void setNewPasswordConfirm(String newPasswordConfirm) {
		this.newPasswordConfirm = newPasswordConfirm;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
	public void logout() {
		this.loggedUser = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        //return "/home.xhtml?faces-redirect=true";
	}
	
    public void updateLoggedUserDetails() {
		log.info("Preparing to update Logged user details");
		userService.updateUser(loggedUser);
			//return("faqja1.xhtml?faces-redirect=true");
    }
    
    
    public void changePassword() {
    	HashSaltedPassword hashSaltGenerator = new HashSaltedPassword();
    	if(loggedUser.getPassword()
    			.equals(hashSaltGenerator.hashGenerate(oldPassword, loggedUser.getSalt())) ) {
    		log.info("Old password is correct! Attempting to change the password now!");
    		if(newPassword.equals(newPasswordConfirm) && !oldPassword.equals(newPasswordConfirm)) {
    			byte[] salt = hashSaltGenerator.createSalt();
    			loggedUser.setPassword(hashSaltGenerator.hashGenerate(newPasswordConfirm, salt));
    			loggedUser.setSalt(salt);
    			userService.updateUser(loggedUser);
    			log.info("Password changed successfully!");
    			UtilMessages.addMessageSuccess("Sukses!", "Fjalekalimi u ndryshua me sukses!");
    		}else {
    			log.error("Passwords weren't entered correctly!");
    			UtilMessages.addMessageError("Error! ", "Fjalekalimet jane futur gabim!");
    		}
    	}else {
    		log.error("Old password is incorrect! ");
    		UtilMessages.addMessageError("Error! ", "Fjalekalimi i vjeter nuk eshte i sakte!");
    	}
    }
}

