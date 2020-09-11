package com.ikubinfo.konferenca.service.impl;

import java.util.ArrayList;

import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikubinfo.konferenca.convert.UserConverter;
import com.ikubinfo.konferenca.dao.UserDao;
import com.ikubinfo.konferenca.dto.UserDto;
import com.ikubinfo.konferenca.entity.User;
import com.ikubinfo.konferenca.managedBeans.UserBean;
import com.ikubinfo.konferenca.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;
	
	@Override
	public UserDto getUserForLoggin(String username) {
		System.out.println("We got at service");
		User user = new User();
		user = userDao.getUserForLogin(username);
		
		if(user != null) {
			return UserConverter.toUserDto(user);
		}else {
			return new UserDto();
		}
	}

	@Override
	public ArrayList<UserDto> getAllUsers() {
		ArrayList<UserDto> usersDto = new ArrayList<UserDto>();
		ArrayList<User> user = userDao.getAllUser();
		
		if(user!=null) {
			
			for(User u : user) {
				usersDto.add(UserConverter.toUserDto(u));
			}
			return usersDto;
		}
		return null;
	}
}
