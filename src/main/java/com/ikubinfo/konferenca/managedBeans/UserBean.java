package com.ikubinfo.konferenca.managedBeans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import com.ikubinfo.konferenca.dto.UserDto;
import com.ikubinfo.konferenca.service.UserService;

@ManagedBean(name = "userBean")
@RequestScoped
public class UserBean {
	
	@ManagedProperty(value = "#{userService}")
	UserService uService;
	
	UserDto userDto;
	
	private UserDto selectedUser;
	private ArrayList<UserDto> selectedUsers = new ArrayList<UserDto>();
	

	public UserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	public UserService getuService() {
		return uService;
	}
	public void setuService(UserService uService) {
		this.uService = uService;
	}
	public UserDto getSelectedUser() {
		return selectedUser;
	}
	public void setSelectedUser(UserDto selectedUser) {
		this.selectedUser = selectedUser;
	}
	public ArrayList<UserDto> getSelectedUsers() {
		return selectedUsers;
	}
	public void setSelectedUsers(ArrayList<UserDto> selectedUsers) {
		this.selectedUsers = selectedUsers;
	}
	
	
	public void logOut() {
		this.userDto = null;
	}
	
	public ArrayList<UserDto> getAllUsers(){
		return uService.getAllUsers();
	}
	
	public void deleteUser() {
		
	}
	
	public void updateUser() {
		
		
	}
	
	
	
}
