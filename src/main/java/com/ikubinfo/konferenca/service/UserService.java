package com.ikubinfo.konferenca.service;

import java.util.ArrayList;
import java.util.List;

import com.ikubinfo.konferenca.dto.UserDto;

public interface UserService {
	UserDto getUserForLoggin(String email);
	ArrayList<UserDto> getAllUsers(); 
	boolean addUser(UserDto userDto);
	boolean updateUser(UserDto userDto);
	boolean deleteUser(List<UserDto> userListToDelete);
}
