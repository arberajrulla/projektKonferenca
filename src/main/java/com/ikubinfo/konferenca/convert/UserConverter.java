package com.ikubinfo.konferenca.convert;

import com.ikubinfo.konferenca.dto.UserDto;
import com.ikubinfo.konferenca.entity.User;

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
		return userDto;
	}
}
