package com.ikubinfo.konferenca.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="shqyrtues")
public class Shqyrtues {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_email")
	private String idEmail;

	@Column(name = "emri")
	private String emri;
	
	@Column(name = "mbiemri")
	private String mbiemri;
	
	@Column(name = "institucioni")
	private String institucioni;
	
	@OneToMany(mappedBy = "shqyrtues", cascade=CascadeType.ALL)
	private List<Artikull> artikujt = new ArrayList<Artikull>();
	

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
	
	public List<Artikull> getArtikujt() {
		return artikujt;
	}

	public void setArtikujt(List<Artikull> artikujt) {
		this.artikujt = artikujt;
	}
}
