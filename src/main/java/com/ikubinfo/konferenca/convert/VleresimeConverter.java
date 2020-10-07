package com.ikubinfo.konferenca.convert;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ikubinfo.konferenca.dto.AutorDto;
import com.ikubinfo.konferenca.dto.ShqyrtuesArtikullDto;
import com.ikubinfo.konferenca.entity.Artikull;
import com.ikubinfo.konferenca.entity.Autor;
import com.ikubinfo.konferenca.entity.Shqyrtues;
import com.ikubinfo.konferenca.entity.ShqyrtuesArtikull;
import com.ikubinfo.konferenca.managedBeans.ShqyrtuesHolderBean;

@Component
public class VleresimeConverter {
	
	private static Logger log = Logger.getLogger(VleresimeConverter.class);
	
	@Autowired
	ShqyrtuesConverter shqyrtuesConverter;
	
	@Autowired
	ArtikullConverter artikullConverter;
	
	@Autowired
	AutorConverter autorConverter;
	
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
	
	
	public ShqyrtuesArtikullDto toShqyrtuesArtikullDtoForAutorConverter(ShqyrtuesArtikull shqyrtuesArtikull) {
		ShqyrtuesArtikullDto shqyrtuesArtikullDto = new ShqyrtuesArtikullDto();
		shqyrtuesArtikullDto.setArid(shqyrtuesArtikull.getArtikullVleresim().getArtikullId());
		shqyrtuesArtikullDto.setShqrtid(shqyrtuesArtikull.getShqyrtuesVleresim().getIdEmail());
		shqyrtuesArtikullDto.setEmriFull(shqyrtuesArtikull.getShqyrtuesVleresim().getEmri() + " " + shqyrtuesArtikull.getShqyrtuesVleresim().getMbiemri());
		shqyrtuesArtikullDto.setMeritaTeknike(shqyrtuesArtikull.getMeritaTeknike());
		shqyrtuesArtikullDto.setKuptueshmeria(shqyrtuesArtikull.getKuptueshmeria());
		shqyrtuesArtikullDto.setOrigjinaliteti(shqyrtuesArtikull.getOrigjinaliteti());
		shqyrtuesArtikullDto.setPerkatesiKonference(shqyrtuesArtikull.getPerkatesiKonference());
		shqyrtuesArtikullDto.setRekomandim(shqyrtuesArtikull.getRekomandim());
		shqyrtuesArtikullDto.setVleresimId(shqyrtuesArtikull.getVleresimId());
		
		shqyrtuesArtikullDto.setShqyrtuesPerVleresimin(shqyrtuesConverter.toShqyrtuesDto(shqyrtuesArtikull.getShqyrtuesVleresim()));

		shqyrtuesArtikullDto.setArtikullPerShqyrtuesin(artikullConverter.toArtikullDto(shqyrtuesArtikull.getArtikullVleresim()));
		return shqyrtuesArtikullDto;
	}
	
	
	public ShqyrtuesArtikullDto toShqyrtuesArtikullDtoForShqyrtuesConverter(ShqyrtuesArtikull shqyrtuesArtikulln) {
		ShqyrtuesArtikullDto shqyrtuesArtikullDton = new ShqyrtuesArtikullDto();
		shqyrtuesArtikullDton.setEmriFull(shqyrtuesArtikulln.getShqyrtuesVleresim().getEmri() + " " + shqyrtuesArtikulln.getShqyrtuesVleresim().getMbiemri());
		shqyrtuesArtikullDton.setMeritaTeknike(shqyrtuesArtikulln.getMeritaTeknike());
		shqyrtuesArtikullDton.setKuptueshmeria(shqyrtuesArtikulln.getKuptueshmeria());
		shqyrtuesArtikullDton.setOrigjinaliteti(shqyrtuesArtikulln.getOrigjinaliteti());
		shqyrtuesArtikullDton.setPerkatesiKonference(shqyrtuesArtikulln.getPerkatesiKonference());
		shqyrtuesArtikullDton.setRekomandim(shqyrtuesArtikulln.getRekomandim());
		shqyrtuesArtikullDton.setVleresimId(shqyrtuesArtikulln.getVleresimId());
		shqyrtuesArtikullDton.setShqrtid(shqyrtuesArtikulln.getShqyrtuesVleresim().getIdEmail());
		shqyrtuesArtikullDton.setArid(shqyrtuesArtikulln.getArtikullVleresim().getArtikullId());
		shqyrtuesArtikullDton.setArtikullPerShqyrtuesin(artikullConverter.toArtikullDto(shqyrtuesArtikulln.getArtikullVleresim()));
		
		List <AutorDto> autorPerArtikullShqyrtues = new ArrayList<AutorDto>();
		for(Autor a : shqyrtuesArtikulln.getArtikullVleresim().getAutoret()) {
			autorPerArtikullShqyrtues.add(autorConverter.toAutorDto(a));
		}
		shqyrtuesArtikullDton.setListaAutorPerArtikullShqyrtues(autorPerArtikullShqyrtues);
		shqyrtuesArtikullDton.setShqyrtuesPerVleresimin(shqyrtuesConverter.toShqyrtuesDto(shqyrtuesArtikulln.getShqyrtuesVleresim()));
		log.info("Into converter for shqyrtues " + shqyrtuesArtikullDton.getShqrtid());
		return shqyrtuesArtikullDton;
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
