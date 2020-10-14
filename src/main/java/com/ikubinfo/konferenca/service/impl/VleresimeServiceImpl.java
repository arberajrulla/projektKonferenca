package com.ikubinfo.konferenca.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ikubinfo.konferenca.convert.VleresimeConverter;
import com.ikubinfo.konferenca.dao.ShqyrtuesArtikullDao;
import com.ikubinfo.konferenca.dto.ShqyrtuesArtikullDto;
import com.ikubinfo.konferenca.entity.Shqyrtues;
import com.ikubinfo.konferenca.entity.ShqyrtuesArtikull;
import com.ikubinfo.konferenca.entity.User;
import com.ikubinfo.konferenca.service.VleresimeService;
import com.ikubinfo.konferenca.utils.UtilMessages;

@Service("vleresimeService")
public class VleresimeServiceImpl implements VleresimeService{

	private static Logger log = Logger.getLogger(VleresimeServiceImpl.class);
	
	@Autowired
	ShqyrtuesArtikullDao shqyrtuesArtikullDao;
	
	@Autowired
	VleresimeConverter vleresimeConverter;
	
	
	@Override
	public List<ShqyrtuesArtikullDto> getShqyrtuesArtikullList() {
		List<ShqyrtuesArtikullDto> listaVleresimeDto = new ArrayList<ShqyrtuesArtikullDto>();
		try {
			for(ShqyrtuesArtikull shqyrtuesArtikull : shqyrtuesArtikullDao.getAllShqyrtuesArtikull()) {
				listaVleresimeDto.add(vleresimeConverter.toShqyrtuesArtikullDto(shqyrtuesArtikull));}
			return listaVleresimeDto;
		} catch (Exception e) {
			log.error("Something went wrong! Couldn't convert ShqyrtuesArtikull list to DTO", e);
		}
		return listaVleresimeDto;
	}

	
	@Transactional
	public boolean addVleresim(ShqyrtuesArtikullDto newVleresim) {
			try {
				shqyrtuesArtikullDao.addShqyrtuesArtikull(vleresimeConverter.toNewShqyrtuesArtikull(newVleresim));
				log.info("New Vleresim for " + newVleresim.getArtikull() + " added successfully!");
				return true;
			} catch (Exception e) {
				log.error("Vleresim Service couldn't add new Vleresim!", e);
			}
			return false;
	}
	
	
	@Transactional
	public boolean deleteSingleVleresim(ShqyrtuesArtikullDto vleresimToDelete) {
		try {
			shqyrtuesArtikullDao.deleteShqyrtuesArtikull(vleresimToDelete.getVleresimId());
			return true;
		} catch (Exception e) {
			log.error("Couldn't delete, error occurred!", e);
		}
		return false;
	}

	
	@Transactional
	public boolean deleteVleresim(List<ShqyrtuesArtikullDto> selectedVleresime) {
		try {
			for(ShqyrtuesArtikullDto shqyrtuesArtikullDto : selectedVleresime) {
				shqyrtuesArtikullDao.deleteShqyrtuesArtikull(shqyrtuesArtikullDto.getVleresimId());
			}
			return true;
		} catch (Exception e) {
			log.error("Couldn't delete, error occurred!", e);
		}
		return false;
	}

	
	@Transactional
	public boolean updateVleresim(ShqyrtuesArtikullDto vleresim) {
			try {
				shqyrtuesArtikullDao.updateShqyrtuesArtikull(vleresimeConverter.toShqyrtuesArtikull(vleresim)); 
				log.info("Service updated Vleresim successfully!");
				return true;
			} catch (Exception e) {
				log.error("Service couldn't update the Vleresim!");
			}
			return false;
	}
	

	@Override
	public List<ShqyrtuesArtikullDto> getShqyrtuesArtikullListForShqyrtues() {
		List<ShqyrtuesArtikullDto> listaVleresimeDto = new ArrayList<ShqyrtuesArtikullDto>();
		try {
			for(ShqyrtuesArtikull shqyrtuesArtikull : shqyrtuesArtikullDao.getAllShqyrtuesArtikull()) {
				listaVleresimeDto.add(vleresimeConverter.toShqyrtuesArtikullDtoForShqyrtuesConverter(shqyrtuesArtikull));
			}
			return listaVleresimeDto;
		} catch (Exception e) {
			log.error("Something went wrong! Couldn't convert ShqyrtuesArtikull list to DTO", e);
		}
		return listaVleresimeDto;
	}
	
	
	@Override
	public boolean vleresimCheck(String shqrtid, int arid) {
		try {
			if(shqyrtuesArtikullDao.checkShqyrtuesArtikullIfExists(shqrtid, arid)) {
				log.error("Vleresim with this combination exists!");
				return true;
			}else {
				log.info("Service, success, Vleresim doesn't exist!");
				return false;
			}
		} catch (Exception e) {
			log.error("Error, Service couldn't get Vleresim!", e);
		}
		return true;
	}

}
