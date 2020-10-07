package com.ikubinfo.konferenca.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ikubinfo.konferenca.convert.ShqyrtuesConverter;
import com.ikubinfo.konferenca.dao.ShqyrtuesDao;
import com.ikubinfo.konferenca.dto.ShqyrtuesDto;
import com.ikubinfo.konferenca.entity.Shqyrtues;
import com.ikubinfo.konferenca.service.ShqyrtuesService;
import com.ikubinfo.konferenca.utils.UtilMessages;

@Service("shqyrtuesService")
public class ShqyrtuesServiceImpl implements ShqyrtuesService{

	private static Logger log = Logger.getLogger(ShqyrtuesServiceImpl.class);
	
	@Autowired
	ShqyrtuesDao shqyrtuesDao;
	
	@Autowired
	ShqyrtuesConverter shqyrtuesConverter;
	
	@Transactional
	public List<ShqyrtuesDto> getAllShqyrtuesList() {
		List<ShqyrtuesDto> listaShqyrtues = new ArrayList<ShqyrtuesDto>();
		try {
			for(Shqyrtues shqyrtues : shqyrtuesDao.getAllShqyrtues()) {
				listaShqyrtues.add(shqyrtuesConverter.toShqyrtuesDto(shqyrtues));
			}
			return listaShqyrtues;
		} catch (Exception e) {
			log.error("Error, Service got an empty Shqyrtues list", e);
			UtilMessages.addMessageError( null, "Problem ne marrjen e listes se shqyrtuesve!");
			return new ArrayList<ShqyrtuesDto>();
		}
	}

	@Transactional
	public void addShqyrtues(ShqyrtuesDto shqyrtuesDto) {
		if (shqyrtuesCheck(shqyrtuesDto.getIdEmail())) {
			UtilMessages.addMessageError(null, "Error, Shqyrtuesi me kete E-mail ekziston!");
		}else {
			try {
				shqyrtuesDao.addShqyrtues(shqyrtuesConverter.toShqyrtues(shqyrtuesDto)); 
				log.info("New Shqyrtues " + shqyrtuesDto.getEmri() + " " + shqyrtuesDto.getMbiemri() + " added successfully!");
				UtilMessages.addMessageSuccess("Sukses!", "Shqyrtuesi " + shqyrtuesDto.getEmri() + " u shtua me sukses!");
			} catch (Exception e) {
				log.error("Shqyrtues Service couldn't add new Shqyrtues!", e);
				UtilMessages.addMessageError(null, "Error, shqyrtuesi nuk u shtua");
			}
		}
	}

	@Transactional
	public void updateShqyrtues(ShqyrtuesDto shqyrtuesDto) {		
			try {
				shqyrtuesDao.updateShqyrtues(shqyrtuesConverter.toShqyrtuesUpdate(shqyrtuesDto)); 
				log.info("Service updated shqyrtues successfully!");
				UtilMessages.addMessageSuccess("Sukses!", "Shqyrtuesi " + shqyrtuesDto.getEmri() + " u modifikua me sukses!");
			} catch (Exception e) {
				log.error("Service couldn't update the shqyrtues!", e);
				UtilMessages.addMessageError(null, "Error, modifikimi nuk u krye!");
			}
	}
	
	@Transactional
	public void updateLoggedShqyrtues(ShqyrtuesDto shqyrtuesDto) {		
			try {
				shqyrtuesDao.updateShqyrtues(shqyrtuesConverter.toLoggedShqyrtuesUpdate(shqyrtuesDto)); 
				log.info("Service updated logged shqyrtues successfully!");
				UtilMessages.addMessageSuccess("Sukses!", "Profili u modifikua me sukses!");
			} catch (Exception e) {
				log.error("Service couldn't update the logged shqyrtues!", e);
				UtilMessages.addMessageError(null, "Error, modifikimi nuk u krye!");
			}
	}	
	
	

	@Transactional
	public void deleteShqyrtues(List<ShqyrtuesDto> shqyrtuesListToDelete) {
		try {
			for(ShqyrtuesDto shqyrtuesDto : shqyrtuesListToDelete) {
				shqyrtuesDao.deleteShqyrtues(shqyrtuesDto.getIdEmail());}
			UtilMessages.addMessageSuccess("Sukses!", "Fshirja u krye me sukses!");
		} catch (Exception e) {
			log.error("Breaking from deletion loop, error occurred!", e);
			UtilMessages.addMessageError(null, "Error, fshirja nuk u krye!");
		}
	}

	@Override
	public boolean shqyrtuesCheck(String idEmail) {
		try {
			if(shqyrtuesDao.checkShqyrtuesIfExists(idEmail)) {
				log.error("Shqyrtues with this email exists!");
				return true;
			}else {
				log.info("Service, success, Shqyrtues doesn't exist!");
				return false;
			}
		} catch (Exception e) {
			log.error("Error, Service couldn't get Shqyrtues!", e);
			UtilMessages.addMessageError(null, "Error, gjate kontrollit, ju lutemi provoni perseri!");
			return true;
		}
	}

	@Override
	public ShqyrtuesDto getShqyrtues(String email) {
		try {
			ShqyrtuesDto shqyrtuesDto;
			shqyrtuesDto = shqyrtuesConverter.toShqyrtuesDtoForAutor(shqyrtuesDao.getShqyrtues(email));
			return shqyrtuesDto;
		} catch (Exception e) {
			log.error("Error, Service couldn't get Shqyrtues!", e);
			UtilMessages.addMessageError(null, "Error, gjate marrjes se shqyrtuesit, ju lutemi provoni perseri!");
			return null;
		}
	}
}
