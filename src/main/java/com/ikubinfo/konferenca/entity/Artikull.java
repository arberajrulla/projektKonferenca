package com.ikubinfo.konferenca.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="artikull")
public class Artikull {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "artikull_id")
	private int artikullId;

	@Column(name = "titulli")	
	private String titulli;
	
	@Column(name = "abstrakti")	
	private String abstrakti;
	
	@Column(name = "dokumenti_elektronik")	
	private String dokumentiElektronik;
	
	@Column(name = "kontakt")	
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
