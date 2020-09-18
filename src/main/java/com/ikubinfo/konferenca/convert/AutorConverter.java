package com.ikubinfo.konferenca.convert;

import com.ikubinfo.konferenca.dto.AutorDto;
import com.ikubinfo.konferenca.entity.Autor;

public class AutorConverter {

	public AutorDto toAutorDto(Autor autor) {
		AutorDto autorDto = new AutorDto();
		autorDto.setEmailId(autor.getEmailId());
		autorDto.setEmri(autor.getEmri());
		autorDto.setMbiemri(autor.getMbiemri());
		autorDto.setArtikullId(autor.getArtikullId());
		autorDto.setId(autor.getId());
		return autorDto;
	}
}
