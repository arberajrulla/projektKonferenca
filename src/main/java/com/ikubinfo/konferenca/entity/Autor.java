package com.ikubinfo.konferenca.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="autor")
public class Autor {
	
	@Id
	@Column(name = "email_id")
	private String emailId;

	@Column(name = "emri")
	private String emri;
	
	@Column(name = "mbiemri")
	private String mbiemri;

	@Column(name = "id")
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "artikull_id")
	private Artikull artikuj;
	
	
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public Artikull getArtikuj() {
		return artikuj;
	}

	public void setArtikuj(Artikull artikuj) {
		this.artikuj = artikuj;
	}

}
