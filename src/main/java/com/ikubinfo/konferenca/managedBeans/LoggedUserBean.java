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

	private UserDto loggedUser = new UserDto();
	private UserDto temporaryLoggedUser = new UserDto();
	private AutorDto loggedAutor = new AutorDto();
	private ShqyrtuesDto loggedShqyrtues = new ShqyrtuesDto();

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
	
	/* First login after completing the final registration successfully */
	
	public void temporaryToPermanentLoggedUser() {
		loggedUser=temporaryLoggedUser;
	}

    
    /* Autor panel methods */
    
    public void updateLoggedAutorDetails() {
    	loggedAutor.setEmri(loggedUser.getEmri());
    	loggedAutor.setMbiemri(loggedUser.getMbiemri());
    	loggedAutor.setEmailId(loggedUser.getEmail());
    	loggedAutor.setArtikullId(loggedAutor.getArtikullDto().getArtikullId());
    	updateLoggedAutor(loggedAutor);
    	updateUser(loggedUser);
    }
    
    public void updateLoggedAutorArtikull() {
    	if(artikullService.updateArtikull(loggedAutor.getArtikullDto())) {
    		UtilMessages.addMessageSuccess(null, 
    				UtilMessages.bundle.getString("ARTIKULL_UPDATE_SUCCESS"));
    	}else {
    		UtilMessages.addMessageError(null, 
    				UtilMessages.bundle.getString("ARTIKULL_UPDATE_FAIL"));
    	}
    }
    
    public void updateLoggedAutor(AutorDto autorDto) {
		if(autorService.updateAutor(autorDto)) {
			UtilMessages.addMessageSuccess(null, 
					UtilMessages.bundle.getString("AUTOR_PROFILE_UPDATE_SUCCESS"));
		}else {
			UtilMessages.addMessageError(null, 
					UtilMessages.bundle.getString("AUTOR_PROFILE_UPDATE_FAIL"));
		}
    }
    
    
	/* Shqyrtues panel methods */
    
    public void updateLoggedShqyrtuesDetails() {
    	loggedShqyrtues.setEmri(loggedUser.getEmri());
    	loggedShqyrtues.setMbiemri(loggedUser.getMbiemri());
    	loggedShqyrtues.setIdEmail(loggedUser.getEmail());
    	updateLoggedShqyrtues(loggedShqyrtues);
    	updateUser(loggedUser);
    }
    

    public void updateLoggedShqyrtues(ShqyrtuesDto shqyrtuesDto) {
		if(shqyrtuesService.updateLoggedShqyrtues(shqyrtuesDto)) {
			UtilMessages.addMessageSuccess(null, 
					UtilMessages.bundle.getString("SHQYRTUES_PROFILE_UPDATE_SUCCESS"));
		}else {
			UtilMessages.addMessageError(null, 
					UtilMessages.bundle.getString("SHQYRTUES_PROFILE_UPDATE_FAIL"));
		}
    }
    
    
    /* Change current Logged User password */
    
    public void changePassword() {
    	HashSaltedPassword hashSaltGenerator = new HashSaltedPassword();
    	if(loggedUser.getPassword()
    			.equals(hashSaltGenerator.hashGenerate(loggedUser.getOldPassword(), loggedUser.getSalt())) ) {
    		log.info("Old password is correct! Attempting to change the password now!");
    		
    		if(loggedUser.getNewPassword().equals(loggedUser.getNewPasswordConfirm()) 
    				&& !loggedUser.getOldPassword().equals(loggedUser.getNewPasswordConfirm())) {
    			byte[] salt = hashSaltGenerator.createSalt();
    			loggedUser.setPassword(hashSaltGenerator.hashGenerate(loggedUser.getNewPasswordConfirm(), salt));
    			loggedUser.setSalt(salt);
    			if (userService.updateUser(loggedUser)) {
        			log.info("Password changed successfully!");
        			UtilMessages.addMessageSuccess(null, 
        					UtilMessages.bundle.getString("PASSWORD_CHANGE_SUCCESS"));
				}else {
	    			log.error("Error, password wasn't changed!");
	    			UtilMessages.addMessageError(null, 
	    					UtilMessages.bundle.getString("PASSWORD_CHANGE_FAIL"));
				}
    			
    		}else {
    			log.error("Passwords weren't entered correctly!");
    			UtilMessages.addMessageError(null, 
    					UtilMessages.bundle.getString("PASSWORDS_DO_NOT_MATCH"));
    		}
    	}else {
    		log.error("Old password is incorrect! ");
    		UtilMessages.addMessageError(null, 
    				UtilMessages.bundle.getString("INCORRECT_OLD_PASSWORD"));
    	}
    }
    
    /* Update current Logged User info */
	
    public void updateLoggedUserDetails() {
		log.info("Preparing to update Logged user details");
		updateUser(loggedUser);
    }
    
    public void updateUser(UserDto userDto) {
		if(userService.updateUser(userDto)) {
			UtilMessages.addMessageSuccess(null, 
					UtilMessages.bundle.getString("USER_UPDATE_SUCCESS"));
		}else {
			UtilMessages.addMessageError(null, 
					UtilMessages.bundle.getString("USER_UPDATE_FAIL"));
		}
    }
    
    /* Delete current Logged User */
    
	public String deleteLoggedUser() {
		try {
			if (userService.deleteSingleUser(loggedUser.getUsername())) {
				this.loggedUser = null;
			}
			
			if(loggedAutor!=null && !loggedAutor.getEmailId().isEmpty()) {
				if (autorService.deleteSingleAutor(loggedAutor)) {
					this.loggedAutor = null;
				}
			}
			
			if(loggedShqyrtues!=null && !loggedShqyrtues.getIdEmail().isEmpty()) {
				if(shqyrtuesService.deleteSingleShqyrtues(loggedShqyrtues)) {
					this.loggedShqyrtues = null;
				}
			}
	        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	        return("/login.xhtml?faces-redirect=true");
		}catch(Exception e){
			log.error("User couldn't be deleted! ", e);
		}
		UtilMessages.addMessageError(null, 
				UtilMessages.bundle.getString("USER_DELETE_FAIL"));
		return null;
	}
	
	public void logout() {
		this.loggedAutor = null;
		this.loggedUser = null;
		this.loggedShqyrtues = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
    
}

