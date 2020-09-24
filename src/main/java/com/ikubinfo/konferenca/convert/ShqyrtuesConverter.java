package com.ikubinfo.konferenca.convert;

import org.springframework.stereotype.Component;

import com.ikubinfo.konferenca.dto.ShqyrtuesDto;
import com.ikubinfo.konferenca.entity.Shqyrtues;

@Component
public class ShqyrtuesConverter {
	
	public static ShqyrtuesDto toShqyrtuesDto(Shqyrtues shqyrtues) {
		ShqyrtuesDto shqyrtuesDto = new ShqyrtuesDto();
		shqyrtuesDto.setIdEmail(shqyrtues.getIdEmail());
		shqyrtuesDto.setEmri(shqyrtues.getEmri());
		shqyrtuesDto.setMbiemri(shqyrtues.getMbiemri());
		shqyrtuesDto.setInstitucioni(shqyrtues.getInstitucioni());
		return shqyrtuesDto;
		}
	
	public static Shqyrtues toShqyrtues(ShqyrtuesDto shqyrtuesDto) {
		Shqyrtues shqyrtues = new Shqyrtues();
		shqyrtues.setIdEmail(shqyrtuesDto.getIdEmail());
		shqyrtues.setEmri(shqyrtuesDto.getEmri());
		shqyrtues.setMbiemri(shqyrtuesDto.getMbiemri());
		shqyrtues.setInstitucioni(shqyrtuesDto.getInstitucioni());
		return shqyrtues;
	}
	
	public static Shqyrtues toShqyrtuesUpdate(ShqyrtuesDto shqyrtuesDto) {
		Shqyrtues shqyrtues = new Shqyrtues();
		shqyrtues.setIdEmail(shqyrtuesDto.getIdEmail());
		shqyrtues.setEmri(shqyrtuesDto.getEmri());
		shqyrtues.setMbiemri(shqyrtuesDto.getMbiemri());
		shqyrtues.setInstitucioni(shqyrtuesDto.getInstitucioni());
		return shqyrtues;
	}
}
