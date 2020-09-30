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

	@Transactional
	public UserDto getUserForLoggin(String username) {
		User user = new User();
		UserDto userDto = new UserDto();
		try {
			user = userDao.getUserForLogin(username);
			userDto = UserConverter.toUserDto(user);
			log.info("Service retrieved user for login successfully!");
			return userDto;
		} catch (Exception e) {
			log.error("Service couldn't retrieve the User for login!", e);
			UtilMessages.addMessageError( null, "Problem ne marrjen e perdoruesit per login!");
			return new UserDto();
		}
	}

	@Transactional
	public ArrayList<UserDto> getAllUsers() {
		ArrayList<UserDto> usersDto = new ArrayList<UserDto>();
		
		try {
			ArrayList<User> user = userDao.getAllUser();
			for(User u : user) {
				usersDto.add(UserConverter.toUserDto(u));
			}
			log.info("Service retrieved user list successfully!");
			return usersDto;
		} catch (Exception e) {
			log.error("Service couldn't retrieve the User list!", e);
			UtilMessages.addMessageError( null, "Problem ne marrjen e listes se perdoruesve!");
			return new ArrayList<UserDto>();
		}
	}

	@Transactional
	public void addUser(UserDto userDto) {
		if (userCheck(userDto.getEmail(), userDto.getUsername())) {
			UtilMessages.addMessageError(null, "Error, Perdoruesi me kete Username ose E-mail ekziston!");
		}else {
			try {
				userDao.addUser(UserConverter.toNewUser(userDto));
				log.info("New User " + userDto.getEmri() + " " + userDto.getMbiemri() + " added successfully!");
				UtilMessages.addMessageSuccess("Sukses!", "Perdoruesi " + userDto.getEmri() + " u shtua me sukses!");
			} catch (Exception e) {
				log.error("User Service couldn't add new user!",e);
				UtilMessages.addMessageError(null, "Error, perdoruesi nuk u shtua");
			}
		}
	}

	@Transactional
	public void updateUser(UserDto userDto) {
		if (userCheck(userDto.getEmail(), userDto.getUsername())) {
			UtilMessages.addMessageError(null, "Error, Perdoruesi me kete Username ose E-mail ekziston!");
		}else {
			try {
				userDao.updateUser(UserConverter.userUpdate(userDto)); 
				log.info("Service updated user successfully!");
				UtilMessages.addMessageSuccess("Sukses!", "Perdoruesi " + userDto.getEmri() + " u modifikua me sukses!");
			} catch (Exception e) {
				log.error("Service couldn't update the user!", e);
				UtilMessages.addMessageError(null, "Error, modifikimi nuk u krye!");
			}
		}
	}


	@Transactional
	public void deleteUser(List<UserDto> userListToDelete) {
		try {
			for(UserDto userDto : userListToDelete) {
			userDao.deleteUser(userDto.getUsername());}
			log.info("Service deleted user successfully!");
			UtilMessages.addMessageSuccess("Sukses!", "Fshirja u krye me sukses!");
		} catch (Exception e) {
			log.error("Couldn't delete user, error occurred!", e);
			UtilMessages.addMessageError(null, "Error, fshirja nuk u krye!");
		}
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
			UtilMessages.addMessageError(null, "Error, gjate kontrollit, ju lutemi provoni perseri!");
			return true;
		}
	}
}
