package com.ikubinfo.konferenca.convert;

import org.springframework.stereotype.Component;

import com.ikubinfo.konferenca.dto.ArtikullDto;
import com.ikubinfo.konferenca.entity.Artikull;

@Component
public class ArtikullConverter {

	public ArtikullDto toArtikullDto(Artikull artikull) {
		ArtikullDto artikullDto = new ArtikullDto();
		artikullDto.setArtikullId(artikull.getArtikullId());
		artikullDto.setTitulli(artikull.getTitulli());
		artikullDto.setAbstrakti(artikull.getAbstrakti());
		artikullDto.setDokumentiElektronik(artikull.getDokumentiElektronik());
		artikullDto.setKontakt(artikull.getKontakt());
		artikullDto.setDocName(artikull.getDocName());
		artikullDto.setDocPicture(artikull.getDocPicture());
		return artikullDto;
	}
	
	//in construction
	public ArtikullDto toArtikullDtoForAutor(Artikull artikull) {
		ArtikullDto artikullDto = new ArtikullDto();
		artikullDto.setArtikullId(artikull.getArtikullId());
		artikullDto.setTitulli(artikull.getTitulli());
		artikullDto.setAbstrakti(artikull.getAbstrakti());
		artikullDto.setDokumentiElektronik(artikull.getDokumentiElektronik());
		artikullDto.setKontakt(artikull.getKontakt());
		artikullDto.setDocName(artikull.getDocName());
		artikullDto.setDocPicture(artikull.getDocPicture());
		
		/*
		 * artikull. artikullDto.setAutorDtoListForArtikull(autorDtoListForArtikull);
		 */
		
		return artikullDto;
	}
	
	
	
	
	public Artikull toArtikull(ArtikullDto artikullDto) {
		Artikull artikull = new Artikull();
		artikull.setArtikullId(artikullDto.getArtikullId());
		artikull.setTitulli(artikullDto.getTitulli());
		artikull.setAbstrakti(artikullDto.getAbstrakti());
		artikull.setDokumentiElektronik(artikullDto.getDokumentiElektronik());
		artikull.setKontakt(artikullDto.getKontakt());
		artikull.setDocName(artikullDto.getDocName());
		artikull.setDocPicture(artikullDto.getDocPicture());
		
		return artikull;
	}
	

	public Artikull toNewArtikull(ArtikullDto artikullIRi) {
		Artikull artikull = new Artikull();
		artikull.setArtikullId(artikullIRi.getArtikullId());
		artikull.setTitulli(artikullIRi.getTitulli());
		artikull.setAbstrakti(artikullIRi.getAbstrakti());
		artikull.setDokumentiElektronik(artikullIRi.getDokumentiElektronik());
		artikull.setKontakt(artikullIRi.getKontakt());
		artikull.setDocName(artikullIRi.getDocName());
		artikull.setDocPicture(artikullIRi.getDocPicture());
		
		return artikull;
	}

	public Artikull toArtikullUpdate(ArtikullDto artikullDto) {
		Artikull artikull = new Artikull();
		artikull.setArtikullId(artikullDto.getArtikullId());
		artikull.setTitulli(artikullDto.getTitulli());
		artikull.setAbstrakti(artikullDto.getAbstrakti());
		artikull.setDokumentiElektronik(artikullDto.getDokumentiElektronik());
		artikull.setKontakt(artikullDto.getKontakt());
		artikull.setDocName(artikullDto.getDocName());
		artikull.setDocPicture(artikullDto.getDocPicture());
		
		return artikull;
	}
	
}
