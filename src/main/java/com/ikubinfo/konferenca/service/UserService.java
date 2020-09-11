package com.ikubinfo.konferenca.service;

import java.util.ArrayList;
import com.ikubinfo.konferenca.dto.UserDto;

public interface UserService {
	UserDto getUserForLoggin(String email);
	ArrayList<UserDto> getAllUsers(); 
}
