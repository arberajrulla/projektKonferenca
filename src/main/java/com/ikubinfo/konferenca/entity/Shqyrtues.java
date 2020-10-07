package com.ikubinfo.konferenca.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="shqyrtues")
public class Shqyrtues {

	
	@Id
	@Column(name = "id_email")
	private String idEmail;

	@Column(name = "emri")
	private String emri;
	
	@Column(name = "mbiemri")
	private String mbiemri;
	
	@Column(name = "institucioni")
	private String institucioni;
	
	
	@OneToMany(mappedBy = "shqyrtuesVleresim", cascade= {CascadeType.REMOVE, CascadeType.MERGE}) 
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<ShqyrtuesArtikull> vleresime = new ArrayList<ShqyrtuesArtikull>();
	
	

	public List<ShqyrtuesArtikull> getVleresime() {
		return vleresime;
	}

	public void setVleresime(List<ShqyrtuesArtikull> vleresime) {
		this.vleresime = vleresime;
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
