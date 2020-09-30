package com.ikubinfo.konferenca.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ikubinfo.konferenca.convert.ArtikullConverter;
import com.ikubinfo.konferenca.convert.AutorConverter;
import com.ikubinfo.konferenca.dao.AutorDao;
import com.ikubinfo.konferenca.dto.ArtikullDto;
import com.ikubinfo.konferenca.dto.AutorDto;
import com.ikubinfo.konferenca.entity.Autor;
import com.ikubinfo.konferenca.entity.User;
import com.ikubinfo.konferenca.service.AutorService;
import com.ikubinfo.konferenca.utils.UtilMessages;

@Service(value = "autorService")
public class AutorServiceImpl implements AutorService{
	
	private static Logger log = Logger.getLogger(AutorServiceImpl.class);
	
	@Autowired
	AutorDao autorDao;

	@Autowired
	AutorConverter autorConverter;
	
	@Transactional
	public List<AutorDto> getAllAutore() {
		List<AutorDto> listaAutorDto = new ArrayList<AutorDto>();
		try {
			log.info("Service - Converting Autor objects retrieved from DB to AutorDto");
			for(Autor autor : autorDao.getAllAutor()) {
				listaAutorDto.add(autorConverter.toAutorDto(autor));
			}
			return listaAutorDto;
			
		}catch(Exception e) {
			log.error("Something went wrong! Couldn't convert Autor list to DTO");
			UtilMessages.addMessageError( null, "Problem ne marrjen e listes se autoreve!");
			return new ArrayList<AutorDto>();
		}
	}

	@Transactional
	public void addAutor(AutorDto newAutor) {
		if (autorCheck(newAutor.getEmailId())) {
			UtilMessages.addMessageError(null, "Error, Autori me kete E-mail ekziston!");
		}else {		
			try {
				log.info(" SERVICE adding autor selected dropdown id: " + newAutor.getArtikullId());
				autorDao.addAutor(AutorConverter.toNewAutor(newAutor)); 
				log.info("New Autor " + newAutor.getEmri() + " added successfully!");
				
				UtilMessages.addMessageSuccess("Sukses!", "Autori " + newAutor.getEmri() + " u shtua me sukses!");
			}catch (Exception e) {
				log.error("AUTOR SERVICE- Something went wrong! Couldn't add Autor", e);
				UtilMessages.addMessageError(null, "Error, autori nuk u shtua");
			}
		}
	}


	@Transactional
	public void deleteAutor(List<AutorDto> selectedAutor) {
		try {
			for(AutorDto autorDto : selectedAutor) {
				autorDao.deleteAutor(autorDto.getEmailId());}
			log.info("Service layer, Selected Autor(s) deleted successfully");
			UtilMessages.addMessageSuccess("Sukses!", "Fshirja u krye me sukses!");
		}catch(Exception e) { 
			log.error("Service layer, error during deletion of Autor!", e);
			UtilMessages.addMessageError(null, "Error, fshirja nuk u krye!");
		}
	}
		

	@Transactional
	public void updateAutor(AutorDto autorDto) {
		if (autorCheck(autorDto.getEmailId())) {
			UtilMessages.addMessageError(null, "Error, Autori me kete E-mail ekziston!");
		}else {	
			try {
				autorDao.updateAutor(AutorConverter.toAutorUpdate(autorDto)); 
				log.info("Service updated Autor successfully!");
				UtilMessages.addMessageSuccess("Sukses!", "Autori " + autorDto.getEmri() + " u modifikua me sukses!");
			} catch (Exception e) {
				log.error("Service layer, error during Autor update!", e);
				UtilMessages.addMessageError(null, "Error, modifikimi nuk u krye!");
			}
		}
	}

	@Override
	public boolean autorCheck(String email) {
		try {
			if(autorDao.checkAutorIfExists(email)) {
				log.error("Autor with this email exists!");
				return true;
			}else {
				log.info("Service, success, Autor doesn't exist!");
				return false;
			}
		} catch (Exception e) {
			log.error("Error, Service couldn't get Autor!", e);
			UtilMessages.addMessageError(null, "Error, gjate kontrollit, ju lutemi provoni perseri!");
			return true;
		}
	}
}

