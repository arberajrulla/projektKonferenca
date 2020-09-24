package com.ikubinfo.konferenca.convert;

import org.springframework.stereotype.Component;

import com.ikubinfo.konferenca.dto.AutorDto;
import com.ikubinfo.konferenca.entity.Artikull;
import com.ikubinfo.konferenca.entity.Autor;

@Component
public class AutorConverter {

	public AutorDto toAutorDto(Autor autor) {
		AutorDto autorDto = new AutorDto();
		autorDto.setEmailId(autor.getEmailId());
		autorDto.setEmri(autor.getEmri());
		autorDto.setMbiemri(autor.getMbiemri());
		autorDto.setArtikullId(autor.getArtikuj().getArtikullId());
		autorDto.setArtikullName(autor.getArtikuj().getTitulli());
		return autorDto;
	}
	
	public static Autor toAutor(AutorDto autorDto) {
		Autor autor = new Autor();
		autor.setEmailId(autorDto.getEmailId());
		autor.setEmri(autorDto.getEmri());
		autor.setMbiemri(autorDto.getMbiemri());
		Artikull artikull = new Artikull();
		artikull.setArtikullId(autorDto.getArtikullId());
		autor.setArtikuj(artikull);
		return autor;
	}

	public static Autor toNewAutor(AutorDto newAutor) {
		Autor autor = new Autor();
		autor.setEmailId(newAutor.getEmailId());
		autor.setEmri(newAutor.getEmri());
		autor.setMbiemri(newAutor.getMbiemri());
		
		Artikull artikull = new Artikull();
		artikull.setArtikullId(newAutor.getArtikullId());
		autor.setArtikuj(artikull);
		return autor;
	}
	
	public static Autor toAutorUpdate(AutorDto autorDto) {
		Autor autor = new Autor();
		autor.setEmailId(autorDto.getEmailId());
		autor.setEmri(autorDto.getEmri());
		autor.setMbiemri(autorDto.getMbiemri());
		
		Artikull artikull = new Artikull();
		artikull.setArtikullId(autorDto.getArtikullId());
		artikull.setTitulli(autorDto.getArtikullName());
		autor.setArtikuj(artikull);
		return autor;
	}
}
