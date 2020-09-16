package com.ikubinfo.konferenca.service.impl;

import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ikubinfo.konferenca.convert.ArtikullConverter;
import com.ikubinfo.konferenca.dao.ArtikullDao;
import com.ikubinfo.konferenca.dto.ArtikullDto;
import com.ikubinfo.konferenca.entity.Artikull;
import com.ikubinfo.konferenca.service.ArtikullService;

@Service("artikullService")
public class ArtikullServiceImpl implements ArtikullService {

	private static Logger log = Logger.getLogger(ArtikullServiceImpl.class);
	
	@Autowired
	ArtikullDao artikullDao;
	
	@SuppressWarnings("unused")
	@Override
	public ArrayList<ArtikullDto> getArtikujLista() {
		ArrayList<ArtikullDto> artikujDtoLista = new ArrayList<ArtikullDto>();
		log.info("Service - Converting Artikull objects retrieved from DB to ArtikullDto");
		for(Artikull artikull : artikullDao.getAllArtikuj()) {
			artikujDtoLista.add(ArtikullConverter.toArtikullDto(artikull));
		}
		
		if(artikujDtoLista==null) {
			log.error("Something went wrong! Couldn't convert Artikull list to DTO");
			return null;
		}
		return artikujDtoLista;
	}

}
