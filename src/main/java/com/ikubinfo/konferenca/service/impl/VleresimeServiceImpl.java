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
	
	@Transactional
	public List<ShqyrtuesArtikullDto> getShqyrtuesArtikullList() {
		List<ShqyrtuesArtikullDto> listaVleresimeDto = new ArrayList<ShqyrtuesArtikullDto>();
		try {		
			
			for(ShqyrtuesArtikull shqyrtuesArtikull : shqyrtuesArtikullDao.getAllShqyrtuesArtikull()) {
				listaVleresimeDto.add(vleresimeConverter.toShqyrtuesArtikullDto(shqyrtuesArtikull));
			}
			return listaVleresimeDto;
		} catch (Exception e) {
			log.error("Something went wrong! Couldn't convert ShqyrtuesArtikull list to DTO", e);
			UtilMessages.addMessageError( null, "Problem ne marrjen e listes se vleresimeve!");
			return new ArrayList<ShqyrtuesArtikullDto>();
		}
	}

	@Transactional
	public void addVleresim(ShqyrtuesArtikullDto newVleresim) {
		if (vleresimCheck(newVleresim.getShqrtid(), newVleresim.getArid())) {
			UtilMessages.addMessageError(null, "Error, Shqyrtuesi e ka vleresuar njehere kete artikull!");
		}else {
			try {
				shqyrtuesArtikullDao.addShqyrtuesArtikull(vleresimeConverter.toNewShqyrtuesArtikull(newVleresim));
				log.info("New Vleresim for " + newVleresim.getArtikull() + " added successfully!");
				UtilMessages.addMessageSuccess("Sukses!", "Vleresimi per " + newVleresim.getArtikull() + " u shtua me sukses!");
			} catch (Exception e) {
				log.error("Vleresim Service couldn't add new Vleresim!", e);
				UtilMessages.addMessageError(null, "Error, vleresimi nuk u shtua");
			}
		}
	}

	@Transactional
	public void deleteVleresim(List<ShqyrtuesArtikullDto> selectedVleresime) {
		try {
			for(ShqyrtuesArtikullDto shqyrtuesArtikullDto : selectedVleresime) {
				shqyrtuesArtikullDao.deleteShqyrtuesArtikull(shqyrtuesArtikullDto.getVleresimId());
			}
			UtilMessages.addMessageSuccess("Sukses!", "Fshirja u krye me sukses!");
		} catch (Exception e) {
			log.error("Couldn't delete, error occurred!", e);
			UtilMessages.addMessageError(null, "Error, fshirja nuk u krye!");
		}
	}

	@Transactional
	public void updateVleresim(ShqyrtuesArtikullDto vleresim) {
		if (vleresimCheck(vleresim.getShqrtid(), vleresim.getArid())) {
			UtilMessages.addMessageError(null, "Error, Shqyrtuesi e ka vleresuar njehere kete artikull!");
		}else {
			try {
				shqyrtuesArtikullDao.updateShqyrtuesArtikull(vleresimeConverter.toShqyrtuesArtikull(vleresim)); 
				log.info("Service updated Vleresim successfully!");
				UtilMessages.addMessageSuccess("Sukses!", "Vleresimi nga shqyrtuesi " + vleresim.getEmriFull() + " u modifikua me sukses!");
			} catch (Exception e) {
				log.error("Service couldn't update the Vleresim!");
				UtilMessages.addMessageError(null, "Error, modifikimi nuk u krye!");
			}
		}
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
			UtilMessages.addMessageError(null, "Error, gjate kontrollit, ju lutemi provoni perseri!");
			return true;
		}
	}

}
