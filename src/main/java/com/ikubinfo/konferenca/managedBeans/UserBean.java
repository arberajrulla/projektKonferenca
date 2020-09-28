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
import org.primefaces.event.RowEditEvent;

import com.ikubinfo.konferenca.dto.UserDto;
import com.ikubinfo.konferenca.service.UserService;

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
		allUsersList = userService.getAllUsers();
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

	
	
	public void addUser() {
		if(userService.addUser(newUser)) {
			log.info("User added succesfully");
		}else {
			log.error("New User wasn't addded, error!");
		}
	}
	
	public void deleteOneUser() {
		
		List<UserDto> oneToDelete = new ArrayList<>();
		oneToDelete.add(selectedUser);
		if(userService.deleteUser(oneToDelete)) {
			log.info("User DELETED SUCCESSFULLY!");
			allUsersList = userService.getAllUsers();
		} else {
			log.error("User wasn't deleted!");
		}
	}
	
	public void deleteMultipleUsers() {
		log.info("Delete Multiple users called " + selectedUsers.get(0).getUsername());
		
		if(userService.deleteUser(selectedUsers)) {
			log.info("User list DELETED SUCCESSFULLY!");
			allUsersList = userService.getAllUsers();
		} else {
			log.error("List of Users wasn't deleted!");
			allUsersList = userService.getAllUsers();
		}
	}
	
	
    public void onRowEdit(RowEditEvent<UserDto> event) {
		log.info("Row edit called: " + event.getObject().getEmri());
		
		if(userService.updateUser(event.getObject())) {
			log.info("User object Updated SUCCESSFULLY!");
		} else {
			log.error("User wasn't updated!");
		}
        
    }
     
    public void onRowCancel(RowEditEvent<UserDto> event) {
    	log.info("Canceled the editing");
    }
	
}
