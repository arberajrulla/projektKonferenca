package com.ikubinfo.konferenca.convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ikubinfo.konferenca.dto.ShqyrtuesArtikullDto;
import com.ikubinfo.konferenca.dto.ShqyrtuesDto;
import com.ikubinfo.konferenca.entity.Shqyrtues;
import com.ikubinfo.konferenca.entity.ShqyrtuesArtikull;

@Component
public class ShqyrtuesConverter {
	
	@Autowired
	ArtikullConverter artikullConverter;
	
	@Autowired
	VleresimeConverter vleresimeConverter;
	
	public ShqyrtuesDto toShqyrtuesDto(Shqyrtues shqyrtues) {
		ShqyrtuesDto shqyrtuesDto = new ShqyrtuesDto();
		shqyrtuesDto.setIdEmail(shqyrtues.getIdEmail());
		shqyrtuesDto.setEmri(shqyrtues.getEmri());
		shqyrtuesDto.setMbiemri(shqyrtues.getMbiemri());
		shqyrtuesDto.setInstitucioni(shqyrtues.getInstitucioni());

		return shqyrtuesDto;
		}
	

	public ShqyrtuesDto toShqyrtuesDtoForAutor(Shqyrtues shqyrtues) {
		ShqyrtuesDto shqyrtuesDto = new ShqyrtuesDto();
		shqyrtuesDto = toShqyrtuesDto(shqyrtues);
		
		List<ShqyrtuesArtikullDto> shqyrtuesArtikullDto = new ArrayList<ShqyrtuesArtikullDto>(); 
		for(ShqyrtuesArtikull vleresimSHA : shqyrtues.getVleresime()) {
			ShqyrtuesArtikullDto tempVleresim = new ShqyrtuesArtikullDto();
			
			tempVleresim.setArtikullPerShqyrtuesin(artikullConverter.toArtikullDto(vleresimSHA.getArtikullVleresim()));
			tempVleresim.setEmriFull(vleresimSHA.getShqyrtuesVleresim().getEmri() + " " + vleresimSHA.getShqyrtuesVleresim().getMbiemri());
			shqyrtuesArtikullDto.add(tempVleresim);
		} 

		shqyrtuesDto.setVleresimePerShqyrtues(shqyrtuesArtikullDto);
		
		return shqyrtuesDto;
	}

	
	public Shqyrtues toShqyrtues(ShqyrtuesDto shqyrtuesDto) {
		Shqyrtues shqyrtues = new Shqyrtues();
		shqyrtues.setIdEmail(shqyrtuesDto.getIdEmail());
		shqyrtues.setEmri(shqyrtuesDto.getEmri());
		shqyrtues.setMbiemri(shqyrtuesDto.getMbiemri());
		shqyrtues.setInstitucioni(shqyrtuesDto.getInstitucioni());
		return shqyrtues;
	}
	
	public Shqyrtues toShqyrtuesUpdate(ShqyrtuesDto shqyrtuesDto) {
		Shqyrtues shqyrtues = new Shqyrtues();
		shqyrtues.setIdEmail(shqyrtuesDto.getIdEmail());
		shqyrtues.setEmri(shqyrtuesDto.getEmri());
		shqyrtues.setMbiemri(shqyrtuesDto.getMbiemri());
		shqyrtues.setInstitucioni(shqyrtuesDto.getInstitucioni());
		
		if (shqyrtuesDto.getVleresimePerShqyrtues()!=null) {
			List<ShqyrtuesArtikull> shqyrtuesArtikull = new ArrayList<ShqyrtuesArtikull>();
			for(ShqyrtuesArtikullDto shqyrtuesArtikullDto : shqyrtuesDto.getVleresimePerShqyrtues()) {
				shqyrtuesArtikull.add(vleresimeConverter.toShqyrtuesArtikull(shqyrtuesArtikullDto));
			}
			shqyrtues.setVleresime(shqyrtuesArtikull);
		}
		
		return shqyrtues;
	}
	
	public Shqyrtues toLoggedShqyrtuesUpdate(ShqyrtuesDto shqyrtuesDto) {
		Shqyrtues shqyrtues = new Shqyrtues();
		shqyrtues.setIdEmail(shqyrtuesDto.getIdEmail());
		shqyrtues.setEmri(shqyrtuesDto.getEmri());
		shqyrtues.setMbiemri(shqyrtuesDto.getMbiemri());
		shqyrtues.setInstitucioni(shqyrtuesDto.getInstitucioni());
		
		return shqyrtues;
	}
	
	
}
