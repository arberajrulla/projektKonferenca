package com.ikubinfo.konferenca.convert;

import com.ikubinfo.konferenca.dto.ShqyrtuesDto;
import com.ikubinfo.konferenca.entity.Shqyrtues;

public class ShqyrtuesConverter {

	
	public ShqyrtuesDto toShqyrtuesDto(Shqyrtues shqyrtues) {
		ShqyrtuesDto shqyrtuesDto = new ShqyrtuesDto();
		shqyrtuesDto.setId_email(shqyrtues.getIdEmail());
		shqyrtuesDto.setEmri(shqyrtues.getEmri());
		shqyrtuesDto.setMbiemri(shqyrtues.getMbiemri());
		shqyrtuesDto.setInstitucioni(shqyrtues.getInstitucioni());
		return shqyrtuesDto;
		}
}
