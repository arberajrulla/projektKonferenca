package com.ikubinfo.konferenca.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class AutorDto {

	private static Logger log = Logger.getLogger(AutorDto.class);
	
	@NotNull(message = "E-mail nuk duhet te jete bosh")
	@Size(message = "E-mail duhet te kete nga 2 deri 40 karaktere!", min= 2, max = 40)
	@Email(message = "Formati i e-mail nuk eshte i sakte!")
	private String emailId;
	
	@NotNull(message = "Emri nuk duhet te jete bosh")
	@Size(message = "Emri duhet te kete nga 2 deri 40 karaktere!", min= 2, max = 40)
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Emri duhet te permbaje vetem shkronja!")
	private String emri;
	
	@NotNull(message = "Mbiemri nuk duhet te jete bosh")
	@Size(message = "Mbiemri duhet te kete nga 2 deri 40 karaktere!", min= 2, max = 40)
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Mbiemri duhet te permbaje vetem shkronja!")
	private String mbiemri;
	
	//@NotNull(message = "Zgjidhni te pakten nje kategori!")
	private int artikullId;
	
	private String artikullName;
	
	private ArtikullDto artikullDto;
	private List<ShqyrtuesArtikullDto> vleresimeDto;
	
	
	public ArtikullDto getArtikullDto() {
		return artikullDto;
	}
	public void setArtikullDto(ArtikullDto artikullDto) {
		this.artikullDto = artikullDto;
	}
	public List<ShqyrtuesArtikullDto> getVleresimeDto() {
		return vleresimeDto;
	}
	public void setVleresimeDto(List<ShqyrtuesArtikullDto> vleresimeDto) {
		this.vleresimeDto = vleresimeDto;
	}
	public String getArtikullName() {
		return artikullName;
	}
	public void setArtikullName(String artikullName) {
		this.artikullName = artikullName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getEmri() {
		return emri;
	}
	public void setEmri(String emri) {
		this.emri = emri;
	}
	public String getMbiemri() {
		return mbiemri;
	}
	public void setMbiemri(String mbiemri) {
		this.mbiemri = mbiemri;
	}
	public int getArtikullId() {
		return artikullId;
	}
	public void setArtikullId(int i) {
		this.artikullId = i;
	}
}
