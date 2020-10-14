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
	
	@Override
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
		}
		return artikujDtoLista;
	}

	@Transactional
	public boolean addArtikull(ArtikullDto artikullIRi) {
		try {
			artikullDao.addArtikull(artikullConverter.toNewArtikull(artikullIRi)); 
			log.info("New artikull " + artikullIRi.getTitulli() + " added successfully!");
			return true;
		} catch (Exception e) {
			log.error("Artikull Service couldn't add new artikull!", e);
			}
		return false;
	}
	
	@Transactional
	public boolean deleteSingleArtikull(ArtikullDto artikullToDelete) {
		try {
			artikullDao.deleteArtikull(artikullToDelete.getArtikullId());
			log.info("Artikull service deleted artikull list successfully!");
			return true;
		} catch (Exception e) {
			log.error("Artikull Service couldn't delete artikull!", e);
		}
		return false;
	}

	@Transactional
	public boolean deleteArtikull(List<ArtikullDto> selectedArtikuj) {
		try {
			 for(ArtikullDto artikullDto : selectedArtikuj) {
				 artikullDao.deleteArtikull(artikullDto.getArtikullId());
			 }
			log.info("Artikull service deleted artikull list successfully!");
			return true;
		} catch (Exception e) {
			log.error("Artikull Service couldn't delete artikull!", e);
		}
		return false;
	}

	@Transactional
	public boolean updateArtikull(ArtikullDto artikullDto) {
			try {
				artikullDao.updateArtikull(artikullConverter.toArtikullUpdate(artikullDto)); 
				log.info("Artikull service updated artikull successfully!");
				return true;
			} catch (Exception e) {
				log.error("Artikull service couldn't update the artikull!", e);
			}
			return false;
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
		}
		return true;
	}

}
