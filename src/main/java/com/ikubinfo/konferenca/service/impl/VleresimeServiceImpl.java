package com.ikubinfo.konferenca.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ikubinfo.konferenca.convert.AutorConverter;
import com.ikubinfo.konferenca.convert.VleresimeConverter;
import com.ikubinfo.konferenca.dao.ShqyrtuesArtikullDao;
import com.ikubinfo.konferenca.dao.ShqyrtuesDao;
import com.ikubinfo.konferenca.dto.AutorDto;
import com.ikubinfo.konferenca.dto.ShqyrtuesArtikullDto;
import com.ikubinfo.konferenca.entity.Autor;
import com.ikubinfo.konferenca.entity.ShqyrtuesArtikull;
import com.ikubinfo.konferenca.service.VleresimeService;

@Service("vleresimeService")
public class VleresimeServiceImpl implements VleresimeService{

	private static Logger log = Logger.getLogger(VleresimeServiceImpl.class);
	
	@Autowired
	ShqyrtuesArtikullDao shqyrtuesArtikullDao;
	
	@Autowired
	VleresimeConverter vleresimeConverter;
	
	@Transactional
	public List<ShqyrtuesArtikullDto> getShqyrtuesArtikullList() {
		List<ShqyrtuesArtikullDto> listaVleresimeDto = new ArrayList<ShqyrtuesArtikullDto>();
		log.info("Service - Converting ShqyrtuesArtikull objects retrieved from DB to ShqyrtuesArtikullDto");
		for(ShqyrtuesArtikull shqyrtuesArtikull : shqyrtuesArtikullDao.getAllShqyrtuesArtikull()) {
			listaVleresimeDto.add(vleresimeConverter.toShqyrtuesArtikullDto(shqyrtuesArtikull));
		}
		if(listaVleresimeDto!=null) {
			return listaVleresimeDto;
		}else {
			log.error("Something went wrong! Couldn't convert ShqyrtuesArtikull list to DTO");
			return null;
		}
	}

	@Transactional
	public boolean addVleresim(ShqyrtuesArtikullDto newVleresim) {
		log.info("SERVICE adding new Vleresim");
		boolean check = false;
		if(shqyrtuesArtikullDao.addShqyrtuesArtikull(vleresimeConverter.toNewShqyrtuesArtikull(newVleresim))) {
		log.info("New Vleresim for " + newVleresim.getArtikull() + " added successfully!");
			check = true;
		}else {
			log.error("Vleresim Service couldn't add new Vleresim!");
		}
		return check;
	}

	@Transactional
	public boolean deleteVleresim(List<ShqyrtuesArtikullDto> selectedVleresime) {
		boolean check = false;
		for(ShqyrtuesArtikullDto shqyrtuesArtikullDto : selectedVleresime) {
			check = shqyrtuesArtikullDao.deleteShqyrtuesArtikull(shqyrtuesArtikullDto.getVleresimId());
			if(!check) {
				log.error("Breaking from deletion loop, error occurred!");
				break;
			}
		}
		return check;
	}

	@Transactional
	public boolean updateVleresim(ShqyrtuesArtikullDto vleresim) {
		boolean check = false;
		if(shqyrtuesArtikullDao.updateShqyrtuesArtikull(vleresimeConverter.toShqyrtuesArtikull(vleresim))) {
			log.info("Service updated Vleresim successfully!");
			check = true;
		}else {
			log.error("Service couldn't update the Vleresim!");
		}
		return check;
	}

}
