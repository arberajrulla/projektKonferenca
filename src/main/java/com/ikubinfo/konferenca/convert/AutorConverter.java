package com.ikubinfo.konferenca.convert;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ikubinfo.konferenca.dto.ArtikullDto;
import com.ikubinfo.konferenca.dto.AutorDto;
import com.ikubinfo.konferenca.dto.ShqyrtuesArtikullDto;
import com.ikubinfo.konferenca.entity.Artikull;
import com.ikubinfo.konferenca.entity.Autor;
import com.ikubinfo.konferenca.entity.ShqyrtuesArtikull;

@Component
public class AutorConverter {
	private static Logger log = Logger.getLogger(AutorConverter.class);
	
	
	@Autowired
	AutorConverter autorConverter;
	
	@Autowired
	ArtikullConverter artikullConverter;
	
	@Autowired
	VleresimeConverter vleresimeConverter;
	
	@Autowired
	ShqyrtuesConverter shqyrtuesConverter;

	public AutorDto toAutorDto(Autor autor) {
		AutorDto autorDto = new AutorDto();
		autorDto.setEmailId(autor.getEmailId());
		autorDto.setEmri(autor.getEmri());
		autorDto.setMbiemri(autor.getMbiemri());
		autorDto.setArtikullId(autor.getArtikuj().getArtikullId());
		autorDto.setArtikullName(autor.getArtikuj().getTitulli());

		return autorDto;
	}
	
	
	public AutorDto toAutorDtoForVleresimeArtikulli(Autor autor) {
		AutorDto autorDto = new AutorDto();
		autorDto.setEmailId(autor.getEmailId());
		autorDto.setEmri(autor.getEmri());
		autorDto.setMbiemri(autor.getMbiemri());
		
		
		List<ShqyrtuesArtikullDto> vleresimeDtoList = new ArrayList<ShqyrtuesArtikullDto>(); 
		
		Artikull artikull = new Artikull();
		artikull = autor.getArtikuj();
		
		for(ShqyrtuesArtikull vleresimSHA : artikull.getShqyrtuesArtikull()) { 
			//ShqyrtuesArtikullDto shqyrtuesArtikullDto = new ShqyrtuesArtikullDto();
			//shqyrtuesArtikullDto.setShqyrtuesPerVleresimin(shqyrtuesConverter.toShqyrtuesDto(vleresimSHA.getShqyrtuesVleresim()));
			//log.info("getshqyrtues per vleresimmmm::: " + shqyrtuesArtikullDto.getShqyrtuesPerVleresimin().getEmri());
			vleresimeDtoList.add(vleresimeConverter.toShqyrtuesArtikullDtoForAutorConverter(vleresimSHA));
		}
		autorDto.setVleresimeDto(vleresimeDtoList);
		log.info("vleresimeDTO LISTA : " + vleresimeDtoList.size());
		
		
		ArtikullDto artDto = artikullConverter.toArtikullDto(autor.getArtikuj());
		List<AutorDto> autorDtoForArtikull = new ArrayList<>();
		for(Autor a : artikull.getAutoret()) { 
			AutorDto au = autorConverter.toAutorDto(a);
			autorDtoForArtikull.add(au);
		}
		log.info("AutorDTO for Artikull LISTA : " + autorDtoForArtikull.size());
		artDto.setAutorDtoListForArtikull(autorDtoForArtikull);
		autorDto.setArtikullDto(artDto);
		
		return autorDto;
	}
	
	
	
	
	public Autor toAutor(AutorDto autorDto) {
		Autor autor = new Autor();
		autor.setEmailId(autorDto.getEmailId());
		autor.setEmri(autorDto.getEmri());
		autor.setMbiemri(autorDto.getMbiemri());
		Artikull artikull = new Artikull();
		artikull.setArtikullId(autorDto.getArtikullId());
		autor.setArtikuj(artikull);	
		return autor;
	}

	public Autor toNewAutor(AutorDto newAutor) {
		Autor autor = new Autor();
		autor.setEmailId(newAutor.getEmailId());
		autor.setEmri(newAutor.getEmri());
		autor.setMbiemri(newAutor.getMbiemri());
		
		Artikull artikull = new Artikull();
		artikull.setArtikullId(newAutor.getArtikullId());
		autor.setArtikuj(artikull);
		return autor;
	}
	
	public Autor toAutorUpdate(AutorDto autorDto) {
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
