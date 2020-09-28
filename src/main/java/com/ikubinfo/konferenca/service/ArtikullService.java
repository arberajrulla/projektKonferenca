package com.ikubinfo.konferenca.service;

import java.util.ArrayList;
import java.util.List;

import com.ikubinfo.konferenca.dto.ArtikullDto;

public interface ArtikullService {
	
	List<ArtikullDto> getArtikujLista();
	boolean addArtikull(ArtikullDto artikullIRi);
	void deleteArtikull(List<ArtikullDto> selectedArtikuj);
	boolean updateArtikull(ArtikullDto artikullDto);
	
}
