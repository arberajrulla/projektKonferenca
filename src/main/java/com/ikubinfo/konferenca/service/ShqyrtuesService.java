package com.ikubinfo.konferenca.service;

import java.util.List;

import com.ikubinfo.konferenca.dto.ShqyrtuesDto;
import com.ikubinfo.konferenca.dto.UserDto;

public interface ShqyrtuesService {
	List<ShqyrtuesDto> getAllShqyrtuesList();
	boolean addShqyrtues(ShqyrtuesDto shqyrtuesDto);
	boolean updateShqyrtues(ShqyrtuesDto shqyrtuesDto);
	boolean deleteShqyrtues(List<ShqyrtuesDto> shqyrtuesListToDelete);
}
