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

@Service(value = "autorService")
public class AutorServiceImpl implements AutorService{
	
	private static Logger log = Logger.getLogger(AutorServiceImpl.class);
	
	@Autowired
	AutorDao autorDao;

	@Autowired
	AutorConverter autorConverter;
	
	@SuppressWarnings("unused")
	@Transactional
	public List<AutorDto> getAllAutore() {
		List<AutorDto> listaAutorDto = new ArrayList<AutorDto>();
		log.info("Service - Converting Autor objects retrieved from DB to AutorDto");
		for(Autor autor : autorDao.getAllAutor()) {
			listaAutorDto.add(autorConverter.toAutorDto(autor));
		}
		if(listaAutorDto!=null) {
			return listaAutorDto;
		}else {
			log.error("Something went wrong! Couldn't convert Autor list to DTO");
			return null;
		}
	}
}
