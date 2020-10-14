package com.ikubinfo.konferenca.convert;

import org.springframework.stereotype.Component;
import com.ikubinfo.konferenca.dto.UserDto;
import com.ikubinfo.konferenca.entity.User;
import com.ikubinfo.konferenca.utils.HashSaltedPassword;

@Component
public class UserConverter {
	
	public static UserDto toUserDto(User u){
		UserDto userDto = new UserDto();
		userDto.setUsername(u.getUsername());
		userDto.setEmri(u.getEmri());
		userDto.setMbiemri(u.getMbiemri());
		userDto.setEmail(u.getEmail());
		userDto.setPassword(u.getPassword());
		userDto.setKategoria(u.getKategoria());
		userDto.setNrcel(u.getNrcel());
		userDto.setSalt(u.getSalt());
		userDto.setRegisterStatus(u.getRegisterStatus());
		return userDto;
	}
	
	
	public static User toUser(UserDto uDto) {
		User user = new User();
		user.setUsername(uDto.getUsername());
		user.setEmri(uDto.getEmri());
		user.setMbiemri(uDto.getMbiemri());
		user.setEmail(uDto.getEmail());
		user.setPassword(uDto.getPassword());
		user.setKategoria(uDto.getKategoria());
		user.setNrcel(uDto.getNrcel());
		user.setRegisterStatus(uDto.getRegisterStatus());
		return user;
	}
	
	public static User toNewUser(UserDto uDto) {
		User user = new User();
		HashSaltedPassword encrypt = new HashSaltedPassword();
		byte[] salt = encrypt.createSalt();
		user.setUsername(uDto.getUsername());
		user.setEmri(uDto.getEmri());
		user.setMbiemri(uDto.getMbiemri());
		user.setEmail(uDto.getEmail());
		user.setPassword(encrypt.hashGenerate(uDto.getPassword(), salt));
		user.setSalt(salt);
		user.setKategoria(uDto.getKategoria());
		user.setNrcel(uDto.getNrcel());
		user.setRegisterStatus(0);
		return user;
	}	
	

	
	public static User userUpdate(UserDto uDto) {
		User user = new User();
		user.setUsername(uDto.getUsername());
		user.setEmri(uDto.getEmri());
		user.setMbiemri(uDto.getMbiemri());
		user.setEmail(uDto.getEmail());
		user.setPassword(uDto.getPassword());
		user.setSalt(uDto.getSalt());
		user.setKategoria(uDto.getKategoria());
		user.setNrcel(uDto.getNrcel());
		user.setRegisterStatus(uDto.getRegisterStatus());
		return user;
	}
	
	public static User userPasswordReturn(UserDto uDto) {
		User user = new User();
		HashSaltedPassword encrypt = new HashSaltedPassword();
		byte[] salt = encrypt.createSalt();
		user.setPassword(encrypt.hashGenerate(uDto.getPassword(), salt));
		user.setSalt(salt);
		return user;
	}
	
	
}
