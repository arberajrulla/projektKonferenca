package com.ikubinfo.konferenca.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ikubinfo.konferenca.convert.ShqyrtuesConverter;
import com.ikubinfo.konferenca.convert.UserConverter;
import com.ikubinfo.konferenca.dao.ShqyrtuesDao;
import com.ikubinfo.konferenca.dto.ShqyrtuesDto;
import com.ikubinfo.konferenca.dto.UserDto;
import com.ikubinfo.konferenca.entity.Shqyrtues;
import com.ikubinfo.konferenca.service.ShqyrtuesService;

@Service("shqyrtuesService")
public class ShqyrtuesServiceImpl implements ShqyrtuesService{

	private static Logger log = Logger.getLogger(ShqyrtuesServiceImpl.class);
	
	@Autowired
	ShqyrtuesDao shqyrtuesDao;
	
	
	@SuppressWarnings("unused")
	@Transactional
	public List<ShqyrtuesDto> getAllShqyrtuesList() {
		
		List<ShqyrtuesDto> listaShqyrtues = new ArrayList<ShqyrtuesDto>();
		for(Shqyrtues shqyrtues : shqyrtuesDao.getAllShqyrtues()) {
			listaShqyrtues.add(ShqyrtuesConverter.toShqyrtuesDto(shqyrtues));
		}
		if(listaShqyrtues!=null) {
			log.info("Service got the Shqyrtues list successfully: " + listaShqyrtues.size() + " objects!");
			return listaShqyrtues;
		}else {
			log.error("Error, Service got an empty Shqyrtues list");
			return null;
		}
	}

	@Transactional
	public boolean addShqyrtues(ShqyrtuesDto shqyrtuesDto) {
		boolean check = false;
		if(shqyrtuesDao.addShqyrtues(ShqyrtuesConverter.toShqyrtues(shqyrtuesDto))) {
		log.info("New Shqyrtues " + shqyrtuesDto.getEmri() + " " + shqyrtuesDto.getMbiemri() + " added successfully!");
			check = true;
		}else {
			log.error("Shqyrtues Service couldn't add new Shqyrtues!");
		}
		return check;
	}

	@Transactional
	public boolean updateShqyrtues(ShqyrtuesDto shqyrtuesDto) {
		boolean check = false;
		if(shqyrtuesDao.updateShqyrtues(ShqyrtuesConverter.toShqyrtuesUpdate(shqyrtuesDto))) {
			log.info("Service updated shqyrtues successfully!");
			check = true;
		}else {
			log.error("Service couldn't update the shqyrtues!");
		}
		return check;
	}

	@Transactional
	public boolean deleteShqyrtues(List<ShqyrtuesDto> shqyrtuesListToDelete) {
		boolean check = false;
		for(ShqyrtuesDto shqyrtuesDto : shqyrtuesListToDelete) {
			check = shqyrtuesDao.deleteShqyrtues(shqyrtuesDto.getIdEmail());
			if(!check) {
				log.error("Breaking from deletion loop, error occurred!");
				break;
			}
		}
		return check;
	}
}
