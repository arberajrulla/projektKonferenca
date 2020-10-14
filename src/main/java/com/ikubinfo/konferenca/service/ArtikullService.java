package com.ikubinfo.konferenca.service;

import java.util.ArrayList;
import java.util.List;

import com.ikubinfo.konferenca.dto.ArtikullDto;

public interface ArtikullService {
	
	List<ArtikullDto> getArtikujLista();
	boolean addArtikull(ArtikullDto artikullIRi);
	boolean deleteSingleArtikull(ArtikullDto artikullToDelete);
	boolean deleteArtikull(List<ArtikullDto> selectedArtikuj);
	boolean updateArtikull(ArtikullDto artikullDto);
	boolean artikullCheck(String dokumentiElektronik);
}
