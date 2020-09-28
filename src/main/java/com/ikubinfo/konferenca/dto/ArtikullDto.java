package com.ikubinfo.konferenca.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ArtikullDto {

	@NotNull
	private int artikullId;
	
	@NotNull(message = "Titulli nuk duhet te jete bosh")
	@Size(message = "Titulli duhet te jete maksimumi 50 karaktere!", max = 50)
	private String titulli;
	
	@NotNull(message = "Abstrakti nuk duhet te jete bosh")
	@Size(message = "Abstrakti nuk mund te kete me shume se 250 karaktere!", max = 250)
	private String abstrakti;
	
	@NotNull(message = "Dokumenti Elektronik nuk duhet te jete bosh")
	@Size(message = "Dokumenti Elektronik nuk mund te kete me shume se 200 karaktere!", max = 200)
	private String dokumentiElektronik;
	
	@NotNull(message = "Numri i kontaktit nuk mund te jete bosh")
	@Size(message = "Numri i kontaktit duhet te kete nga 8 deri 40 karaktere!", min= 8, max = 40)
	@Pattern(regexp = "^[0-9+]*$", message = "Numri i kontaktit mund te permbaje vetem numra dhe simbolin \"+\"!")
	private String kontakt;

	public int getArtikullId() {
		return artikullId;
	}
	public void setArtikullId(int artikullId) {
		this.artikullId = artikullId;
	}
	public String getTitulli() {
		return titulli;
	}
	public void setTitulli(String titulli) {
		this.titulli = titulli;
	}
	public String getAbstrakti() {
		return abstrakti;
	}
	public void setAbstrakti(String abstrakti) {
		this.abstrakti = abstrakti;
	}
	public String getDokumentiElektronik() {
		return dokumentiElektronik;
	}
	public void setDokumentiElektronik(String dokumentiElektronik) {
		this.dokumentiElektronik = dokumentiElektronik;
	}
	public String getKontakt() {
		return kontakt;
	}
	public void setKontakt(String kontakt) {
		this.kontakt = kontakt;
	}

}
