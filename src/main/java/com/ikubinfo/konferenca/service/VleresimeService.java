package com.ikubinfo.konferenca.service;

import java.util.List;
import com.ikubinfo.konferenca.dto.ShqyrtuesArtikullDto;

public interface VleresimeService {
	List<ShqyrtuesArtikullDto> getShqyrtuesArtikullList();

	boolean addVleresim(ShqyrtuesArtikullDto newVleresim);
	boolean deleteVleresim(List<ShqyrtuesArtikullDto> selectedVleresime);
	boolean updateVleresim(ShqyrtuesArtikullDto vleresim);
	
}
