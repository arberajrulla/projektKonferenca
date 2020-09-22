package com.ikubinfo.konferenca.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ikubinfo.konferenca.convert.UserConverter;
import com.ikubinfo.konferenca.dao.UserDao;
import com.ikubinfo.konferenca.dto.UserDto;
import com.ikubinfo.konferenca.entity.User;
import com.ikubinfo.konferenca.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	private static Logger log = Logger.getLogger(UserServiceImpl.class);
	
	
	@Autowired
	UserDao userDao;

	@Transactional
	public UserDto getUserForLoggin(String username) {
		User user = new User();
		user = userDao.getUserForLogin(username);
		
		if(user != null) {
			return UserConverter.toUserDto(user);
		}else {
			return new UserDto();
		}
	}

	@Transactional
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

	@Transactional
	public boolean addUser(UserDto userDto) {
		boolean check = false;
		if(userDao.addUser(UserConverter.toNewUser(userDto))) {
		log.info("New User " + userDto.getEmri() + " " + userDto.getMbiemri() + " added successfully!");
			check = true;
		}else {
			log.error("User Service couldn't add new user!");
		}
		return check;
	}

	@Transactional
	public boolean updateUser(UserDto userDto) {
		boolean check = false;
		if(userDao.updateUser(UserConverter.userUpdate(userDto))) {
			log.info("Service updated user successfully!");
			check = true;
		}else {
			log.error("Service couldn't update the user!");
		}
		return check;
	}


	@Transactional
	public boolean deleteUser(List<UserDto> userListToDelete) {
		boolean check = false;
		for(UserDto userDto : userListToDelete) {
			check = userDao.deleteUser(userDto.getUsername());
			if(!check) {
				log.error("Breaking from deletion loop, error occurred!");
				break;
			}
		}
		return check;
	}

}
