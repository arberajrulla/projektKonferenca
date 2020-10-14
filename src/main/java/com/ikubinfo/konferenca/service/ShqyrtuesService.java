package com.ikubinfo.konferenca.service;

import java.util.List;
import com.ikubinfo.konferenca.dto.ShqyrtuesDto;

public interface ShqyrtuesService {
	List<ShqyrtuesDto> getAllShqyrtuesList();
	ShqyrtuesDto getShqyrtues(String email);
	boolean addShqyrtues(ShqyrtuesDto shqyrtuesDto);
	boolean updateShqyrtues(ShqyrtuesDto shqyrtuesDto);
	boolean updateLoggedShqyrtues(ShqyrtuesDto shqyrtuesDto);
	boolean deleteSingleShqyrtues(ShqyrtuesDto shqyrtuesToDelete);
	boolean deleteShqyrtues(List<ShqyrtuesDto> shqyrtuesListToDelete);
	boolean shqyrtuesCheck(String idEmail);
	
}
