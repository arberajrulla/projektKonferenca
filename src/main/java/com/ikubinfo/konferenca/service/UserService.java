package com.ikubinfo.konferenca.service;

import java.util.ArrayList;
import java.util.List;

import com.ikubinfo.konferenca.dto.UserDto;

public interface UserService {
	UserDto getUserForLoggin(String username);
	List<UserDto> getAllUsers(); 
	boolean addUser(UserDto userDto);
	boolean updateUser(UserDto userDto);
	boolean deleteSingleUser(String username);
	boolean deleteUser(List<UserDto> userListToDelete);
	boolean userCheck(String email, String username);
	UserDto getSingleUser(String email);
	boolean recoverUserPassword(UserDto userDto);
}
