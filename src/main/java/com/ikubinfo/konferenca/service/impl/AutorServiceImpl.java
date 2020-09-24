package com.ikubinfo.konferenca.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ikubinfo.konferenca.convert.ArtikullConverter;
import com.ikubinfo.konferenca.convert.AutorConverter;
import com.ikubinfo.konferenca.dao.AutorDao;
import com.ikubinfo.konferenca.dto.ArtikullDto;
import com.ikubinfo.konferenca.dto.AutorDto;
import com.ikubinfo.konferenca.entity.Autor;
import com.ikubinfo.konferenca.service.AutorService;

@Service(value = "autorService")
public class AutorServiceImpl implements AutorService{
	
	private static Logger log = Logger.getLogger(AutorServiceImpl.class);
	
	@Autowired
	AutorDao autorDao;

	@Autowired
	AutorConverter autorConverter;
	
	@SuppressWarnings("unused")
	@Transactional
	public List<AutorDto> getAllAutore() {
		List<AutorDto> listaAutorDto = new ArrayList<AutorDto>();
		log.info("Service - Converting Autor objects retrieved from DB to AutorDto");
		for(Autor autor : autorDao.getAllAutor()) {
			listaAutorDto.add(autorConverter.toAutorDto(autor));
		}
		if(listaAutorDto!=null) {
			return listaAutorDto;
		}else {
			log.error("Something went wrong! Couldn't convert Autor list to DTO");
			return null;
		}
	}

	@Transactional
	public boolean addAutor(AutorDto newAutor) {
		log.info("SERVICE SERVICE SERVICE adding autor selected dropdown id: " + newAutor.getArtikullId());
		boolean check = false;
		if(autorDao.addAutor(AutorConverter.toNewAutor(newAutor))) {
		log.info("New Autor " + newAutor.getEmri() + " added successfully!");
			check = true;
		}else {
			log.error("Autor Service couldn't add new Autor!");
		}
		return check;
	}

	@Transactional
	public boolean deleteAutor(List<AutorDto> selectedAutor) {
		boolean check = false;
		for(AutorDto autorDto : selectedAutor) {
			check = autorDao.deleteAutor(autorDto.getEmailId());
			if(!check) {
				log.error("Breaking from deletion loop, error occurred!");
				break;
			}
		}
		return check;
	}

	@Transactional
	public boolean updateAutor(AutorDto autorDto) {
		boolean check = false;
		if(autorDao.updateAutor(AutorConverter.toAutorUpdate(autorDto))) {
			log.info("Service updated Autor successfully!");
			check = true;
		}else {
			log.error("Service couldn't update the Autor!");
		}
		return check;
	}
}
