package com.ikubinfo.konferenca.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="autor")
public class Autor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "email_id")
	private String email_id;

	@Column(name = "emri")
	private String emri;
	
	@Column(name = "mbiemri")
	private String mbiemri;
	
	@Column(name = "artikull_id")
	private int artikull_id;	

	@Column(name = "id")
	private int id; 
	
	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
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

	public int getArtikull_id() {
		return artikull_id;
	}

	public void setArtikull_id(int artikull_id) {
		this.artikull_id = artikull_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
	
}
