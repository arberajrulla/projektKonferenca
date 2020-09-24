package com.ikubinfo.konferenca.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ArtikullDto {

	@NotNull
	private int artikullId;
	@NotNull
	@Size(max = 50)
	private String titulli;
	private String abstrakti;
	private String dokumentiElektronik;
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
