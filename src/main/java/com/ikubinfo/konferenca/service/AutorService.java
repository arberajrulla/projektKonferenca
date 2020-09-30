package com.ikubinfo.konferenca.service;

import java.util.List;

import com.ikubinfo.konferenca.dto.ArtikullDto;
import com.ikubinfo.konferenca.dto.AutorDto;

public interface AutorService {
	List<AutorDto> getAllAutore();

	void addAutor(AutorDto newAutor);
	void deleteAutor(List<AutorDto> selectedAutor);
	void updateAutor(AutorDto autorDto);
	boolean autorCheck(String email);
}
