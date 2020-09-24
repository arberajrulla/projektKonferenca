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
		
		return artikullDto;
	}
	
	public static Artikull toArtikull(ArtikullDto artikullDto) {
		Artikull artikull = new Artikull();
		artikull.setArtikullId(artikullDto.getArtikullId());
		artikull.setTitulli(artikullDto.getTitulli());
		artikull.setAbstrakti(artikullDto.getAbstrakti());
		artikull.setDokumentiElektronik(artikullDto.getDokumentiElektronik());
		artikull.setKontakt(artikullDto.getKontakt());
		
		return artikull;
	}
	

	public static Artikull toNewArtikull(ArtikullDto artikullIRi) {
		Artikull artikull = new Artikull();
		artikull.setArtikullId(artikullIRi.getArtikullId());
		artikull.setTitulli(artikullIRi.getTitulli());
		artikull.setAbstrakti(artikullIRi.getAbstrakti());
		artikull.setDokumentiElektronik(artikullIRi.getDokumentiElektronik());
		artikull.setKontakt(artikullIRi.getKontakt());
		
		return artikull;
	}

	public static Artikull toArtikullUpdate(ArtikullDto artikullDto) {
		Artikull artikull = new Artikull();
		artikull.setArtikullId(artikullDto.getArtikullId());
		artikull.setTitulli(artikullDto.getTitulli());
		artikull.setAbstrakti(artikullDto.getAbstrakti());
		artikull.setDokumentiElektronik(artikullDto.getDokumentiElektronik());
		artikull.setKontakt(artikullDto.getKontakt());
		
		return artikull;
	}
	
}
