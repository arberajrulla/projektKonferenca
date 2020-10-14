package com.ikubinfo.konferenca.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ikubinfo.konferenca.convert.AutorConverter;
import com.ikubinfo.konferenca.dao.AutorDao;
import com.ikubinfo.konferenca.dto.AutorDto;
import com.ikubinfo.konferenca.entity.Autor;
import com.ikubinfo.konferenca.service.AutorService;
import com.ikubinfo.konferenca.utils.UtilMessages;

@Service(value = "autorService")
public class AutorServiceImpl implements AutorService{
	
	private static Logger log = Logger.getLogger(AutorServiceImpl.class);
	
	@Autowired
	AutorDao autorDao;

	@Autowired
	AutorConverter autorConverter;
	
	@Override
	public List<AutorDto> getAllAutore() {
		List<AutorDto> listaAutorDto = new ArrayList<AutorDto>();
		try {
			log.info("Service - Converting Autor objects retrieved from DB to AutorDto");
			for(Autor autor : autorDao.getAllAutor()) {
				listaAutorDto.add(autorConverter.toAutorDto(autor));}
			return listaAutorDto;
		}catch(Exception e) {
			log.error("Something went wrong! Couldn't convert Autor list to DTO");
		}
		return listaAutorDto;
	}

	@Transactional
	public boolean addAutor(AutorDto newAutor) {
			try {
				autorDao.addAutor(autorConverter.toNewAutor(newAutor)); 
				log.info("New Autor " + newAutor.getEmri() + " added successfully!");
				return true;
			}catch (Exception e) {
				log.error("AUTOR SERVICE- Something went wrong! Couldn't add Autor", e);
			}
		return false;
	}

	
	@Transactional
	public boolean deleteSingleAutor(AutorDto autorDto) {
		try {
			autorDao.deleteAutor(autorDto.getEmailId());
			log.info("Service layer, Autor deleted successfully");
			return true;
		}catch(Exception e) { 
			log.error("Service layer, error during deletion of Autor!", e);
		}
		return false;
	}

	@Transactional
	public boolean deleteAutor(List<AutorDto> selectedAutor) {
		try {
			for(AutorDto autorDto : selectedAutor) {
				autorDao.deleteAutor(autorDto.getEmailId());}
			log.info("Service layer, Selected Autor(s) deleted successfully");
			return true;
		}catch(Exception e) { 
			log.error("Service layer, error during deletion of Autor!", e);
		}
		return false;
	}
		

	@Transactional
	public boolean updateAutor(AutorDto autorDto) {
			try {
				autorDao.updateAutor(autorConverter.toAutorUpdate(autorDto)); 
				log.info("Service updated Autor successfully!");
				return true;
			} catch (Exception e) {
				log.error("Service layer, error during Autor update!", e);
			}
			return false;
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
		}
		return true;
	}

	@Override
	public AutorDto getAutor(String email) {
		try {
			AutorDto autorDto;
			autorDto = autorConverter.toAutorDtoForVleresimeArtikulli(autorDao.getAutor(email));
			return autorDto;
		} catch (Exception e) {
			log.error("Error, Service couldn't get Autor!", e);
		}
		return null;
	}

	@Override
	public AutorDto getAutorByArtikullId(int artikullId) {
		AutorDto autorDto = new AutorDto();
		try {
			log.info("Getting autor by id: " + artikullId);
			autorDto = autorConverter.toAutorDtoForVleresimeArtikulli(autorDao.getAutorByArtikullId(artikullId));
			return autorDto;
		} catch (Exception e) {
			log.error("Error, Service couldn't get Autor!", e);
		}
		return autorDto;
	}

}

