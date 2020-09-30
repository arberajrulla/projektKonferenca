package com.ikubinfo.konferenca.service;

import java.util.List;

import com.ikubinfo.konferenca.dto.ShqyrtuesDto;
import com.ikubinfo.konferenca.dto.UserDto;

public interface ShqyrtuesService {
	List<ShqyrtuesDto> getAllShqyrtuesList();
	void addShqyrtues(ShqyrtuesDto shqyrtuesDto);
	void updateShqyrtues(ShqyrtuesDto shqyrtuesDto);
	void deleteShqyrtues(List<ShqyrtuesDto> shqyrtuesListToDelete);
	boolean shqyrtuesCheck(String idEmail);
}
