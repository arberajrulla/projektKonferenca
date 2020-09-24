package com.ikubinfo.konferenca.convert;

import org.springframework.stereotype.Component;

import com.ikubinfo.konferenca.dto.ShqyrtuesArtikullDto;
import com.ikubinfo.konferenca.entity.Artikull;
import com.ikubinfo.konferenca.entity.Shqyrtues;
import com.ikubinfo.konferenca.entity.ShqyrtuesArtikull;

@Component
public class VleresimeConverter {
	
	public ShqyrtuesArtikullDto toShqyrtuesArtikullDto(ShqyrtuesArtikull shqyrtuesArtikull) {
		ShqyrtuesArtikullDto shqyrtuesArtikullDto = new ShqyrtuesArtikullDto(); 
		shqyrtuesArtikullDto.setArid(shqyrtuesArtikull.getArtikullVleresim().getArtikullId());
		shqyrtuesArtikullDto.setArtikull(shqyrtuesArtikull.getArtikullVleresim().getTitulli());
		shqyrtuesArtikullDto.setShqrtid(shqyrtuesArtikull.getShqyrtuesVleresim().getIdEmail());
		shqyrtuesArtikullDto.setEmriFull(shqyrtuesArtikull.getShqyrtuesVleresim().getEmri() + " " + shqyrtuesArtikull.getShqyrtuesVleresim().getMbiemri());
		shqyrtuesArtikullDto.setMeritaTeknike(shqyrtuesArtikull.getMeritaTeknike());
		shqyrtuesArtikullDto.setKuptueshmeria(shqyrtuesArtikull.getKuptueshmeria());
		shqyrtuesArtikullDto.setOrigjinaliteti(shqyrtuesArtikull.getOrigjinaliteti());
		shqyrtuesArtikullDto.setPerkatesiKonference(shqyrtuesArtikull.getPerkatesiKonference());
		shqyrtuesArtikullDto.setRekomandim(shqyrtuesArtikull.getRekomandim());
		shqyrtuesArtikullDto.setVleresimId(shqyrtuesArtikull.getVleresimId());
		
		return shqyrtuesArtikullDto;
	}
	
	
	public ShqyrtuesArtikull toShqyrtuesArtikull(ShqyrtuesArtikullDto shqyrtuesArtikullDto) {
		ShqyrtuesArtikull shqyrtuesArtikull = new ShqyrtuesArtikull(); 
		
		Artikull artikull = new Artikull();
		artikull.setArtikullId(shqyrtuesArtikullDto.getArid());
		shqyrtuesArtikull.setArtikullVleresim(artikull);
		
		Shqyrtues shqyrtues= new Shqyrtues();
		shqyrtues.setIdEmail(shqyrtuesArtikullDto.getShqrtid());
		shqyrtuesArtikull.setShqyrtuesVleresim(shqyrtues);
		
		shqyrtuesArtikull.setMeritaTeknike(shqyrtuesArtikullDto.getMeritaTeknike());
		shqyrtuesArtikull.setKuptueshmeria(shqyrtuesArtikullDto.getKuptueshmeria());
		shqyrtuesArtikull.setOrigjinaliteti(shqyrtuesArtikullDto.getOrigjinaliteti());
		shqyrtuesArtikull.setPerkatesiKonference(shqyrtuesArtikullDto.getPerkatesiKonference());
		shqyrtuesArtikull.setRekomandim(shqyrtuesArtikullDto.getRekomandim());
		shqyrtuesArtikull.setVleresimId(shqyrtuesArtikullDto.getVleresimId());
		
		return shqyrtuesArtikull;
	}	
	
	
	
	public ShqyrtuesArtikull toNewShqyrtuesArtikull(ShqyrtuesArtikullDto shqyrtuesArtikullDto) {
		ShqyrtuesArtikull shqyrtuesArtikull = new ShqyrtuesArtikull(); 
		
		Artikull artikull = new Artikull();
		artikull.setArtikullId(shqyrtuesArtikullDto.getArid());
		shqyrtuesArtikull.setArtikullVleresim(artikull);
		
		Shqyrtues shqyrtues= new Shqyrtues();
		shqyrtues.setIdEmail(shqyrtuesArtikullDto.getShqrtid());
		shqyrtuesArtikull.setShqyrtuesVleresim(shqyrtues);
		
		shqyrtuesArtikull.setMeritaTeknike(shqyrtuesArtikullDto.getMeritaTeknike());
		shqyrtuesArtikull.setKuptueshmeria(shqyrtuesArtikullDto.getKuptueshmeria());
		shqyrtuesArtikull.setOrigjinaliteti(shqyrtuesArtikullDto.getOrigjinaliteti());
		shqyrtuesArtikull.setPerkatesiKonference(shqyrtuesArtikullDto.getPerkatesiKonference());
		shqyrtuesArtikull.setRekomandim(shqyrtuesArtikullDto.getRekomandim());
		
		return shqyrtuesArtikull;
	}

}
