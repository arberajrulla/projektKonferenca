package com.ikubinfo.konferenca.managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.NotEmpty;

import com.ikubinfo.konferenca.dto.AutorDto;
import com.ikubinfo.konferenca.dto.ShqyrtuesDto;
import com.ikubinfo.konferenca.dto.UserDto;
import com.ikubinfo.konferenca.service.ArtikullService;
import com.ikubinfo.konferenca.service.AutorService;
import com.ikubinfo.konferenca.service.ShqyrtuesService;
import com.ikubinfo.konferenca.service.UserService;
import com.ikubinfo.konferenca.utils.HashSaltedPassword;
import com.ikubinfo.konferenca.utils.UtilMessages;

@ManagedBean(name = "loggedUserBean")
@SessionScoped
public class LoggedUserBean {

	private static Logger log = Logger.getLogger(LoggedUserBean.class);	
	
	@ManagedProperty (value = "#{userService}")
	UserService userService;

	@ManagedProperty (value = "#{autorService}")
	AutorService autorService;

	@ManagedProperty (value = "#{shqyrtuesService}")
	ShqyrtuesService shqyrtuesService;
	
	@ManagedProperty (value = "#{artikullService}")
	ArtikullService artikullService;

	private UserDto loggedUser;
	
	private UserDto temporaryLoggedUser;
	
	private AutorDto loggedAutor = new AutorDto();
	
	private ShqyrtuesDto loggedShqyrtues = new ShqyrtuesDto();

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
	public UserDto getTemporaryLoggedUser() {
		return temporaryLoggedUser;
	}
	public void setTemporaryLoggedUser(UserDto temporaryLoggedUser) {
		this.temporaryLoggedUser = temporaryLoggedUser;
	}
	public AutorDto getLoggedAutor() {
		return loggedAutor;
	}
	public void setLoggedAutor(AutorDto loggedAutor) {
		this.loggedAutor = loggedAutor;
	}
	public ShqyrtuesDto getLoggedShqyrtues() {
		return loggedShqyrtues;
	}
	public void setLoggedShqyrtues(ShqyrtuesDto loggedShqyrtues) {
		this.loggedShqyrtues = loggedShqyrtues;
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
	public ArtikullService getArtikullService() {
		return artikullService;
	}
	public void setArtikullService(ArtikullService artikullService) {
		this.artikullService = artikullService;
	}
	
	
	
	public void temporaryToPermanentLoggedUser() {
		loggedUser=temporaryLoggedUser;
	}
	
	
	public void logout() {
		this.loggedAutor = null;
		this.loggedUser = null;
		this.loggedShqyrtues = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
	
    public void updateLoggedUserDetails() {
		log.info("Preparing to update Logged user details");
		userService.updateUser(loggedUser);
    }
    
    public void updateLoggedAutorDetails() {
    	loggedAutor.setEmri(loggedUser.getEmri());
    	loggedAutor.setMbiemri(loggedUser.getMbiemri());
    	loggedAutor.setEmailId(loggedUser.getEmail());
    	loggedAutor.setArtikullId(loggedAutor.getArtikullDto().getArtikullId());
    	autorService.updateAutor(loggedAutor);
    	userService.updateUser(loggedUser);
    }
    
    public void updateLoggedAutorArtikull() {
    	artikullService.updateArtikull(loggedAutor.getArtikullDto());
    }
    
    
	/* Shqyrtues panel methods */
    
    public void updateLoggedShqyrtuesDetails() {
    	loggedShqyrtues.setEmri(loggedUser.getEmri());
    	loggedShqyrtues.setMbiemri(loggedUser.getMbiemri());
    	loggedShqyrtues.setIdEmail(loggedUser.getEmail());
    	shqyrtuesService.updateLoggedShqyrtues(loggedShqyrtues);
    	userService.updateUser(loggedUser);
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
    
	public String deleteLoggedUser() {
		List<UserDto> lst = new ArrayList<UserDto>();
		lst.add(loggedUser);
		userService.deleteUser(lst);
		this.loggedUser = null;
		
		if(loggedAutor!=null && !loggedAutor.getEmailId().isEmpty()) {
			List<AutorDto> lstA = new ArrayList<AutorDto>();
			lstA.add(loggedAutor);
			autorService.deleteAutor(lstA);
			this.loggedAutor = null;
		}
		
		if(loggedShqyrtues!=null && !loggedShqyrtues.getIdEmail().isEmpty()) {
			List<ShqyrtuesDto> lstS = new ArrayList<ShqyrtuesDto>();
			lstS.add(loggedShqyrtues);
			shqyrtuesService.deleteShqyrtues(lstS);
			this.loggedShqyrtues = null;
		}
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return("/login.xhtml?faces-redirect=true");
	}

    
}

