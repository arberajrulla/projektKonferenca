package com.ikubinfo.konferenca.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;

import com.ikubinfo.konferenca.dto.UserDto;
import com.ikubinfo.konferenca.service.UserService;
import com.ikubinfo.konferenca.utils.UtilMessages;

@ManagedBean(name = "userBean")
@ViewScoped
public class UserBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(UserBean.class);
	
	@ManagedProperty(value = "#{userService}")
	UserService userService;

	private UserDto newUser = new UserDto();
	private UserDto selectedUser;
	private List<UserDto> filteredUsers;
	private List<UserDto> allUsersList;
	private List<UserDto> selectedUsers = new ArrayList<UserDto>();
	
	@PostConstruct
	public void init() {
		fillAllUserList();
	}
	
	public List<UserDto> getAllUsersList() {
		return allUsersList;
	}
	public void setAllUsersList(List<UserDto> allUsersList) {
		this.allUsersList = allUsersList;
	}
	public UserDto getNewUser() {
		return newUser;
	}
	public void setNewUser(UserDto newUser) {
		this.newUser = newUser;
	}
	public UserDto getSelectedUser() {
		return selectedUser;
	}
	public void setSelectedUser(UserDto selectedUser) {
		this.selectedUser = selectedUser;
	}
	public List<UserDto> getSelectedUsers() {
		return selectedUsers;
	}
	public void setSelectedUsers(List<UserDto> selectedUsers) {
		this.selectedUsers = selectedUsers;
	}	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public List<UserDto> getFilteredUsers() {
		return filteredUsers;
	}
	public void setFilteredUsers(List<UserDto> filteredUsers) {
		this.filteredUsers = filteredUsers;
	}

	public void fillAllUserList() {
		allUsersList = userService.getAllUsers();
	}
	
	public void addUser() {
		log.info("Adding new User");
		
		if (!(userService.userCheck(newUser.getEmail(), newUser.getUsername()))) {
			if(userService.addUser(newUser)) {
				UtilMessages.addMessageSuccess(null, 
						UtilMessages.bundle.getString("USER_ADD_SUCCESS"));
				fillAllUserList();
			}else {
				UtilMessages.addMessageError(null, 
						UtilMessages.bundle.getString("USER_ADD_FAIL"));
			}
		}else {
			UtilMessages.addMessageError(null, 
					UtilMessages.bundle.getString("USER_EXISTS"));
		}
	}
	
	public void deleteMultipleUsers() {
		log.info("Delete Multiple users called " + selectedUsers.get(0).getUsername());
		
		if (userService.deleteUser(selectedUsers)) {
			UtilMessages.addMessageSuccess(null, 
					UtilMessages.bundle.getString("USER_DELETE_SUCCESS"));
		} else {
			UtilMessages.addMessageError(null, 
					UtilMessages.bundle.getString("USER_DELETE_FAIL"));
		}
		fillAllUserList();
	}
	
    public void onRowEdit(RowEditEvent<UserDto> event) {
		log.info("Row edit called: " + event.getObject().getEmri());
		
		if (userService.updateUser(event.getObject())) {
			UtilMessages.addMessageSuccess(null, 
					UtilMessages.bundle.getString("USER_UPDATE_SUCCESS"));
		} else {
			UtilMessages.addMessageError(null, 
					UtilMessages.bundle.getString("USER_UPDATE_FAIL"));
		}
    }
     
    public void onRowCancel(RowEditEvent<UserDto> event) {
    	log.info("Canceled the editing");
    	UtilMessages.addCustomMessage(null, 
    			UtilMessages.bundle.getString("INFO_CANCEL"));
    }
	
}
