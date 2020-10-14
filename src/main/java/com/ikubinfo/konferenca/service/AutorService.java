package com.ikubinfo.konferenca.service;

import java.util.List;

import com.ikubinfo.konferenca.dto.ArtikullDto;
import com.ikubinfo.konferenca.dto.AutorDto;

public interface AutorService {
	List<AutorDto> getAllAutore();
	AutorDto getAutor(String email);
	AutorDto getAutorByArtikullId(int artikullId);
	boolean addAutor(AutorDto newAutor);
	boolean deleteSingleAutor(AutorDto autorDto);
	boolean deleteAutor(List<AutorDto> selectedAutor);
	boolean updateAutor(AutorDto autorDto);
	boolean autorCheck(String email);
}
