package com.ikubinfo.konferenca.service;

import java.util.ArrayList;
import java.util.List;

import com.ikubinfo.konferenca.dto.ArtikullDto;

public interface ArtikullService {
	
	List<ArtikullDto> getArtikujLista();
	void addArtikull(ArtikullDto artikullIRi);
	void deleteArtikull(List<ArtikullDto> selectedArtikuj);
	void updateArtikull(ArtikullDto artikullDto);
	boolean artikullCheck(String dokumentiElektronik);
}
