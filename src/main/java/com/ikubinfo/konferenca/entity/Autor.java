package com.ikubinfo.konferenca.entity;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="autor")
public class Autor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "email_id")
	private String emailId;

	@Column(name = "emri")
	private String emri;
	
	@Column(name = "mbiemri")
	private String mbiemri;
	
	@Column(name = "artikull_id")
	private int artikullId;	

	/*
	 * @OneToMany(mappedBy="artikullId",cascade=CascadeType.ALL) public
	 * ArrayList<Artikull> artikuj;
	 */
	
	@Column(name = "id")
	private int id; 
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String email_id) {
		this.emailId = email_id;
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

	public void setArtikullId(int artikull_id) {
		this.artikullId = artikull_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
}
