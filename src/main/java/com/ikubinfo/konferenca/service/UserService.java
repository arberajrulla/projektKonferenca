package com.ikubinfo.konferenca.service;

import java.util.ArrayList;
import java.util.List;

import com.ikubinfo.konferenca.dto.UserDto;

public interface UserService {
	UserDto getUserForLoggin(String email);
	ArrayList<UserDto> getAllUsers(); 
	void addUser(UserDto userDto);
	void updateUser(UserDto userDto);
	void deleteUser(List<UserDto> userListToDelete);
	boolean userCheck(String email, String username);
}
