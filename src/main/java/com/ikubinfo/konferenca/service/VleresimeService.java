package com.ikubinfo.konferenca.service;

import java.util.List;
import com.ikubinfo.konferenca.dto.ShqyrtuesArtikullDto;

public interface VleresimeService {
	List<ShqyrtuesArtikullDto> getShqyrtuesArtikullList();
	List<ShqyrtuesArtikullDto> getShqyrtuesArtikullListForShqyrtues();
	void addVleresim(ShqyrtuesArtikullDto newVleresim);
	void deleteVleresim(List<ShqyrtuesArtikullDto> selectedVleresime);
	void updateVleresim(ShqyrtuesArtikullDto vleresim);
	boolean vleresimCheck(String shqrtid, int arid);
}
