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
	private int artikull_id;

	@Column(name = "titulli")	
	private String titulli;
	
	@Column(name = "abstrakti")	
	private String abstrakti;
	
	@Column(name = "dokumenti_elektronik")	
	private String dokumenti_elektronik;
	
	@Column(name = "kontakt")	
	private String kontakt;
	
	
	public int getArtikull_id() {
		return artikull_id;
	}

	public void setArtikull_id(int artikull_id) {
		this.artikull_id = artikull_id;
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

	public String getDokumenti_elektronik() {
		return dokumenti_elektronik;
	}

	public void setDokumenti_elektronik(String dokumenti_elektronik) {
		this.dokumenti_elektronik = dokumenti_elektronik;
	}

	public String getKontakt() {
		return kontakt;
	}

	public void setKontakt(String kontakt) {
		this.kontakt = kontakt;
	}
}
