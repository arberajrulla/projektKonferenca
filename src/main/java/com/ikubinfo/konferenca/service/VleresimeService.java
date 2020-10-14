package com.ikubinfo.konferenca.service;

import java.util.List;
import com.ikubinfo.konferenca.dto.ShqyrtuesArtikullDto;

public interface VleresimeService {
	List<ShqyrtuesArtikullDto> getShqyrtuesArtikullList();
	List<ShqyrtuesArtikullDto> getShqyrtuesArtikullListForShqyrtues();
	boolean addVleresim(ShqyrtuesArtikullDto newVleresim);
	boolean deleteSingleVleresim(ShqyrtuesArtikullDto vleresimToDelete);
	boolean deleteVleresim(List<ShqyrtuesArtikullDto> selectedVleresime);
	boolean updateVleresim(ShqyrtuesArtikullDto vleresim);
	boolean vleresimCheck(String shqrtid, int arid);
}
