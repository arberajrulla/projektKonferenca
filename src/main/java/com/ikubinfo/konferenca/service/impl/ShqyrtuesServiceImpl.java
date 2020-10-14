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
	
	@Override
	public List<ShqyrtuesDto> getAllShqyrtuesList() {
		List<ShqyrtuesDto> listaShqyrtues = new ArrayList<ShqyrtuesDto>();
		try {
			for(Shqyrtues shqyrtues : shqyrtuesDao.getAllShqyrtues()) {
				listaShqyrtues.add(shqyrtuesConverter.toShqyrtuesDto(shqyrtues));}
			return listaShqyrtues;
		} catch (Exception e) {
			log.error("Error, Service got an empty Shqyrtues list", e);
		}
		return listaShqyrtues;
	}

	@Transactional
	public boolean addShqyrtues(ShqyrtuesDto shqyrtuesDto) {
		try {
			shqyrtuesDao.addShqyrtues(shqyrtuesConverter.toShqyrtues(shqyrtuesDto)); 
			log.info("New Shqyrtues " + shqyrtuesDto.getEmri() + " " + shqyrtuesDto.getMbiemri() + " added successfully!");
			return true;
		} catch (Exception e) {
			log.error("Shqyrtues Service couldn't add new Shqyrtues!", e);
		}
		return false;
	}

	@Transactional
	public boolean updateShqyrtues(ShqyrtuesDto shqyrtuesDto) {		
			try {
				shqyrtuesDao.updateShqyrtues(shqyrtuesConverter.toShqyrtuesUpdate(shqyrtuesDto)); 
				log.info("Service updated shqyrtues successfully!");
				return true;
			} catch (Exception e) {
				log.error("Service couldn't update the shqyrtues!", e);
			}
			return false;
	}
	
	@Transactional
	public boolean updateLoggedShqyrtues(ShqyrtuesDto shqyrtuesDto) {		
			try {
				shqyrtuesDao.updateShqyrtues(shqyrtuesConverter.toLoggedShqyrtuesUpdate(shqyrtuesDto)); 
				log.info("Service updated logged shqyrtues successfully!");
				return true;
			} catch (Exception e) {
				log.error("Service couldn't update the logged shqyrtues!", e);
			}
			return false;
	}	
	
	@Transactional
	public boolean deleteSingleShqyrtues(ShqyrtuesDto shqyrtuesToDelete) {
		try {
			shqyrtuesDao.deleteShqyrtues(shqyrtuesToDelete.getIdEmail());
			return true;
		} catch (Exception e) {
			log.error("Deletion error occurred!", e);
		}
		return false;
	}

	@Transactional
	public boolean deleteShqyrtues(List<ShqyrtuesDto> shqyrtuesListToDelete) {
		try {
			for(ShqyrtuesDto shqyrtuesDto : shqyrtuesListToDelete) {
				shqyrtuesDao.deleteShqyrtues(shqyrtuesDto.getIdEmail());}
			return true;
		} catch (Exception e) {
			log.error("Breaking from deletion loop, error occurred!", e);
		}
		return false;
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
			return true;
		}
	}

	@Override
	public ShqyrtuesDto getShqyrtues(String email) {
		ShqyrtuesDto shqyrtuesDto = new ShqyrtuesDto();
		try {
			shqyrtuesDto = shqyrtuesConverter.toShqyrtuesDtoForAutor(shqyrtuesDao.getShqyrtues(email));
		} catch (Exception e) {
			log.error("Error, Service couldn't get Shqyrtues!", e);
		}
		return shqyrtuesDto;
	}

}
