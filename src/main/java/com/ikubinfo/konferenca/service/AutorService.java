package com.ikubinfo.konferenca.service;

import java.util.List;

import com.ikubinfo.konferenca.dto.ArtikullDto;
import com.ikubinfo.konferenca.dto.AutorDto;

public interface AutorService {
	List<AutorDto> getAllAutore();

	boolean addAutor(AutorDto newAutor);
	boolean deleteAutor(List<AutorDto> selectedAutor);
	boolean updateAutor(AutorDto autorDto);
}
