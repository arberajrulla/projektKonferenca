package com.ikubinfo.konferenca.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="shqyrtues")
public class Shqyrtues {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_email")
	private String id_email;
	
	@Column(name = "emri")
	private String emri;
	
	@Column(name = "mbiemri")
	private String mbiemri;
	
	@Column(name = "institucioni")
	private String institucioni;
	
	
	public String getId_email() {
		return id_email;
	}

	public void setId_email(String id_email) {
		this.id_email = id_email;
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
