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
import com.ikubinfo.konferenca.utils.UtilMessages;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	private static Logger log = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserDao userDao;

	
	@Override
	public UserDto getUserForLoggin(String username) {
		UserDto userDto = new UserDto();
		try {
			userDto = UserConverter.toUserDto(userDao.getUserForLogin(username));
			log.info("Service retrieved user for login successfully!");
			return userDto;
		} catch (Exception e) {
			log.error("Service couldn't retrieve the User for login!", e);
		}
		return null;
	}

	
	@Override
	public List<UserDto> getAllUsers() {
		List<UserDto> usersDto = new ArrayList<UserDto>();
		try {
			for(User u : userDao.getAllUser()) {
				usersDto.add(UserConverter.toUserDto(u));}
			log.info("Service retrieved user list successfully!");
			return usersDto;
		} catch (Exception e) {
			log.error("Service couldn't retrieve the User list!", e);
		}
		return usersDto;
	}

	
	@Transactional
	public boolean addUser(UserDto userDto) {
			try {
				userDao.addUser(UserConverter.toNewUser(userDto));
				log.info("New User " + userDto.getEmri() + " " + userDto.getMbiemri() + " added successfully!");
				return true;
			} catch (Exception e) {
				log.error("User Service couldn't add new user!",e);
			}
		return false;
	}

	
	@Transactional
	public boolean updateUser(UserDto userDto) {
			try {
				userDao.updateUser(UserConverter.userUpdate(userDto)); 
				log.info("Service updated user successfully!");
				return true;
			} catch (Exception e) {
				log.error("Service couldn't update the user!", e);
			}
		return false;
	}

	
	@Transactional
	public boolean deleteSingleUser(String username) {
		try {
			userDao.deleteUser(username);
			log.info("Service deleted user successfully!");
			return true;
		} catch (Exception e) {
			log.error("Couldn't delete user, error occurred!", e);
		}
		return false;
	}

	
	@Transactional
	public boolean deleteUser(List<UserDto> userListToDelete) {
		try {
			for(UserDto userDto : userListToDelete) {
			userDao.deleteUser(userDto.getUsername());}
			log.info("Service deleted user successfully!");
			return true;
		} catch (Exception e) {
			log.error("Couldn't delete user, error occurred!", e);
		}
		return false;
	}

	
	@Override
	public boolean userCheck(String email, String username) {
		try {
			if(userDao.checkUserIfExists(email, username)) {
				log.error("User with this email or username exists!");
				return true;
			}else {
				log.info("Service, success, User doesn't exist!");
				return false;
			}
		} catch (Exception e) {
			log.error("Error, Service couldn't get user!", e);
		}
		return true;
	}

	
	@Override
	public UserDto getSingleUser(String email) {
		UserDto userDto = new UserDto();
		try {
			userDto = UserConverter.toUserDto(userDao.getSingleUser(email));
			log.info("Service retrieved user successfully!");
			return userDto;
		} catch (Exception e) {
			log.error("Service couldn't retrieve the User!", e);
		}
		return null;
	}

	
	@Transactional
	public boolean recoverUserPassword(UserDto userDto) {
		try {
			userDao.updateUser(UserConverter.userPasswordReturn(userDto)); 
			log.info("Service recovered user password successfully!");
			return true;
		} catch (Exception e) {
			log.error("Service couldn't update the user password!", e);
		}
		return false;
	}

}
