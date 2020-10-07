package com.ikubinfo.konferenca.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class ShqyrtuesDto {

	@NotEmpty(message = "E-mail nuk duhet te jete bosh")
	@Size(message = "E-mail duhet te kete nga 2 deri 40 karaktere!", min= 2, max = 40)
	@Email(message = "Formati i e-mail nuk eshte i sakte!")
	private String idEmail;

	@NotNull(message = "Emri nuk duhet te jete bosh")
	@Size(message = "Emri duhet te kete nga 2 deri 40 karaktere!", min= 2, max = 40)
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Emri duhet te permbaje vetem shkronja!")
	private String emri;
	
	@NotNull(message = "Mbiemri nuk duhet te jete bosh")
	@Size(message = "Mbiemri duhet te kete nga 2 deri 40 karaktere!", min= 2, max = 40)
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Mbiemri duhet te permbaje vetem shkronja!")
	private String mbiemri;

	@NotNull(message = "Institucioni nuk duhet te jete bosh")
	@Size(message = "Institucioni nuk duhet te kete me shume se 250 karaktere!", max = 250)
	private String institucioni;
	
	private List<ShqyrtuesArtikullDto> vleresimePerShqyrtues;
	
	
	public List<ShqyrtuesArtikullDto> getVleresimePerShqyrtues() {
		return vleresimePerShqyrtues;
	}
	public void setVleresimePerShqyrtues(List<ShqyrtuesArtikullDto> vleresimePerShqyrtues) {
		this.vleresimePerShqyrtues = vleresimePerShqyrtues;
	}
	public String getIdEmail() {
		return idEmail;
	}
	public void setIdEmail(String idEmail) {
		this.idEmail = idEmail;
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
	public String getInstitucioni() {
		return institucioni;
	}
	public void setInstitucioni(String institucioni) {
		this.institucioni = institucioni;
	}
	
}
