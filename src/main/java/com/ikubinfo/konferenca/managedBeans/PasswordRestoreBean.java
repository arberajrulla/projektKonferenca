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


@ManagedBean(name = "passwordRestoreBean")
@SessionScoped
public class PasswordRestoreBean {

	private static Logger log = Logger.getLogger(PasswordRestoreBean.class);
	
	@NotNull(message = "E-mail nuk duhet te jete bosh")
	@Size(message = "E-mail duhet te kete nga 2 deri 40 karaktere!", min= 2, max = 40)
	@Email(message = "Formati i e-mail nuk eshte i sakte!")
	private String email;
	
	private String kodi;
	private boolean errorKey = false;

	@NotNull(message = "Kodi nuk duhet te jete bosh")
	@Size(message = "Kodi duhet te kete 6 karaktere!", min= 6, max = 6)
	private String enteredKodi;
	
	@NotEmpty(message = "Fjalekalimi nuk mund te jete bosh!")
	@Size(min=6, max=20, message = "Fjalekalimi duhet te kete nga 6 deri 20 karaktere!")
	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[@!#$%^&+=]).{6,}$", message = "Fjalekalimi duhet te permbaje te pakten nje numer dhe nje karakter special (@,!,#,$,%,^,&,+,=)!")
	private String newPassword;

	@NotEmpty(message = "Fjalekalimi nuk mund te jete bosh!")
	@Size(min=6, max=20, message = "Fjalekalimi duhet te kete nga 6 deri 20 karaktere!")
	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[@!#$%^&+=]).{6,}$", message = "Fjalekalimi duhet te permbaje te pakten nje numer dhe nje karakter special (@,!,#,$,%,^,&,+,=)!")
	private String newPasswordConfirm;
	
	
	
	@ManagedProperty(value = "#{userService}")
	UserService userService;

	@ManagedProperty(value = "#{loggedUserBean}")
	LoggedUserBean loggedUserBean;
	
	@ManagedProperty (value = "#{autorService}")
	AutorService autorService;

	@ManagedProperty (value = "#{shqyrtuesService}")
	ShqyrtuesService shqyrtuesService;
	
	

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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getKodi() {
		return kodi;
	}
	public void setKodi(String kodi) {
		this.kodi = kodi;
	}
	public String getEnteredKodi() {
		return enteredKodi;
	}
	public void setEnteredKodi(String enteredKodi) {
		this.enteredKodi = enteredKodi;
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
	public boolean isErrorKey() {
		return errorKey;
	}
	public void setErrorKey(boolean errorKey) {
		this.errorKey = errorKey;
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
	
	
	
	public void sendEmail() {
		UserDto userDto = userService.getSingleUser(email);
		if(userDto.getEmail().equals(email)) {
			kodi = MailUtil.codeGenerate();
			log.info("kodi per resetim : " + kodi);
			MailUtil.sendResetMail(email, kodi);
		}
	}
	
	public String verifikoKodin() {
		if(kodi.equals(enteredKodi)) {
			UserDto userDto = userService.getSingleUser(email);
			loggedUserBean.setLoggedUser(userDto);
			errorKey = false;
			return "/harrimFjalekalimiConfirm.xhtml?faces-redirect=true";
		}else {
			errorKey = true;
			return null;
		}
		
	}
	
	public String setNewPassword() {
		if(newPassword.equals(newPasswordConfirm)) {
			UserDto u = new UserDto();
			userService.recoverUserPassword(u);
			
			return loginAfterPaswordReset();
		}else {
			return null;
		}
	}
	
	public String loginAfterPaswordReset() {

		UserDto userDto = userService.getSingleUser(email);
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
			return null;
		}
		
	}
	
	
	
}
