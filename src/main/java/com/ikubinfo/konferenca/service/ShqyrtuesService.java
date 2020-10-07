package com.ikubinfo.konferenca.service;

import java.util.List;
import com.ikubinfo.konferenca.dto.ShqyrtuesDto;

public interface ShqyrtuesService {
	List<ShqyrtuesDto> getAllShqyrtuesList();
	ShqyrtuesDto getShqyrtues(String email);
	void addShqyrtues(ShqyrtuesDto shqyrtuesDto);
	void updateShqyrtues(ShqyrtuesDto shqyrtuesDto);
	void updateLoggedShqyrtues(ShqyrtuesDto shqyrtuesDto);
	void deleteShqyrtues(List<ShqyrtuesDto> shqyrtuesListToDelete);
	boolean shqyrtuesCheck(String idEmail);
	
}
