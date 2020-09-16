package com.ikubinfo.konferenca.convert;

import com.ikubinfo.konferenca.dto.ArtikullDto;
import com.ikubinfo.konferenca.entity.Artikull;

public class ArtikullConverter {

	public static ArtikullDto toArtikullDto(Artikull artikull) {
		ArtikullDto artikullDto = new ArtikullDto();
		artikullDto.setArtikullId(artikull.getArtikullId());
		artikullDto.setTitulli(artikull.getTitulli());
		artikullDto.setAbstrakti(artikull.getAbstrakti());
		artikullDto.setDokumentiElektronik(artikull.getDokumentiElektronik());
		artikullDto.setKontakt(artikull.getKontakt());
		
		return artikullDto;
	}
}
