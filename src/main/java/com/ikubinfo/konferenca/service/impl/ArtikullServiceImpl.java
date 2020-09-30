package com.ikubinfo.konferenca.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ikubinfo.konferenca.convert.ArtikullConverter;
import com.ikubinfo.konferenca.dao.ArtikullDao;
import com.ikubinfo.konferenca.dto.ArtikullDto;
import com.ikubinfo.konferenca.entity.Artikull;
import com.ikubinfo.konferenca.entity.Autor;
import com.ikubinfo.konferenca.service.ArtikullService;
import com.ikubinfo.konferenca.utils.UtilMessages;

@Service(value ="artikullService")
public class ArtikullServiceImpl implements ArtikullService {

	private static Logger log = Logger.getLogger(ArtikullServiceImpl.class);
	
	@Autowired
	ArtikullDao artikullDao;
	
	@Autowired
	ArtikullConverter artikullConverter;
	
	@Transactional
	public List<ArtikullDto> getArtikujLista() {
		List<ArtikullDto> artikujDtoLista = new ArrayList<ArtikullDto>();
		
		try {
			log.info("Service - Converting Artikull objects retrieved from DB to ArtikullDto");
			for(Artikull artikull : artikullDao.getAllArtikuj()) {
				artikujDtoLista.add(artikullConverter.toArtikullDto(artikull));}	
				log.info("Returning service from DB to ArtikullDto " + artikujDtoLista.size());
				
				return artikujDtoLista;
		} catch (Exception e) {
			log.error("Something went wrong fetching Artikull list", e);
			UtilMessages.addMessageError(null, "Error, Autoret nuk u moren!");
			return new ArrayList<ArtikullDto>();
		}
	}

	@Transactional
	public void addArtikull(ArtikullDto artikullIRi) {
		if (artikullCheck(artikullIRi.getDokumentiElektronik())) {
			UtilMessages.addMessageError(null, "Error, Artikulli me kete Dokument Elektronik ekziston!");
		}else {
			try {
				artikullDao.addArtikull(ArtikullConverter.toNewArtikull(artikullIRi)); 
				log.info("New artikull " + artikullIRi.getTitulli() + " added successfully!");
				UtilMessages.addMessageSuccess("Sukses!", "Artikulli " + artikullIRi.getTitulli() + " u shtua me sukses!");
			} catch (Exception e) {
				log.error("Artikull Service couldn't add new artikull!", e);
				UtilMessages.addMessageError(null, "Error, artikulli nuk u shtua");
			}
		}
	}

	@Transactional
	public void deleteArtikull(List<ArtikullDto> selectedArtikuj) {
		try {
			 for(ArtikullDto artikullDto : selectedArtikuj) {
				 artikullDao.deleteArtikull(artikullDto.getArtikullId());
			 }
			log.info("Artikull service deleted artikull list successfully!");
			UtilMessages.addMessageSuccess("Sukses!", "Fshirja u krye me sukses!");
		} catch (Exception e) {
			log.error("Artikull Service couldn't delete artikull!", e);
			UtilMessages.addMessageError(null, "Error, fshirja nuk u krye!");
		}
	}

	@Transactional
	public void updateArtikull(ArtikullDto artikullDto) {
		if (artikullCheck(artikullDto.getDokumentiElektronik())) {
			UtilMessages.addMessageError(null, "Error, Artikulli me kete Dokument Elektronik ekziston!");
		}else {	
			try {
				artikullDao.updateArtikull(ArtikullConverter.toArtikullUpdate(artikullDto)); 
				log.info("Artikull service updated artikull successfully!");
				UtilMessages.addMessageSuccess("Sukses!", "Artikulli " + artikullDto.getTitulli() + " u modifikua me sukses!");
			} catch (Exception e) {
				log.error("Artikull service couldn't update the artikull!", e);
				UtilMessages.addMessageError(null, "Error, modifikimi nuk u krye!");
			}
		}
	}

	@Override
	public boolean artikullCheck(String dokumentiElektronik) {
		try {
			if(artikullDao.checkArtikullIfExists(dokumentiElektronik)) {
				log.error("Artikulli with this e-doc exists!");
				return true;
			}else {
				log.info("Service, success, Artikull doesn't exist!");
				return false;
			}
		} catch (Exception e) {
			log.error("Error, Service couldn't get Artikull!", e);
			UtilMessages.addMessageError(null, "Error, gjate kontrollit, ju lutemi provoni perseri!");
			return true;
		}
	}
}
