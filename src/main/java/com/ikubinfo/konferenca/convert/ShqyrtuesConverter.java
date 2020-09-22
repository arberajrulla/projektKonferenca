package com.ikubinfo.konferenca.convert;

import org.springframework.stereotype.Component;

import com.ikubinfo.konferenca.dto.ShqyrtuesDto;
import com.ikubinfo.konferenca.entity.Shqyrtues;

@Component
public class ShqyrtuesConverter {
	
	public ShqyrtuesDto toShqyrtuesDto(Shqyrtues shqyrtues) {
		ShqyrtuesDto shqyrtuesDto = new ShqyrtuesDto();
		shqyrtuesDto.setIdEmail(shqyrtues.getIdEmail());
		shqyrtuesDto.setEmri(shqyrtues.getEmri());
		shqyrtuesDto.setMbiemri(shqyrtues.getMbiemri());
		shqyrtuesDto.setInstitucioni(shqyrtues.getInstitucioni());
		return shqyrtuesDto;
		}
}
