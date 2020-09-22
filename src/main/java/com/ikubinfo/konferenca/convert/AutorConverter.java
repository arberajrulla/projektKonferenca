package com.ikubinfo.konferenca.convert;

import org.springframework.stereotype.Component;

import com.ikubinfo.konferenca.dto.AutorDto;
import com.ikubinfo.konferenca.entity.Autor;

@Component
public class AutorConverter {

	public AutorDto toAutorDto(Autor autor) {
		AutorDto autorDto = new AutorDto();
		autorDto.setEmailId(autor.getEmailId());
		autorDto.setEmri(autor.getEmri());
		autorDto.setMbiemri(autor.getMbiemri());
		autorDto.setArtikullId(autor.getArtikuj().getArtikullId());
		autorDto.setId(autor.getId());
		autorDto.setArtikullName(autor.getArtikuj().getTitulli());
		return autorDto;
	}
}
