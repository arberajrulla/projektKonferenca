package com.ikubinfo.konferenca.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
	
	@Column(name = "doc_name")	
	private String docName;

	@Column(name = "doc_picture")	
	private String docPicture;
	
	@OneToMany(mappedBy = "artikuj", cascade=CascadeType.REMOVE)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Autor> autoret = new ArrayList<Autor>();
	
	@OneToMany(mappedBy = "artikullVleresim", fetch = FetchType.EAGER, cascade= CascadeType.REMOVE)
	private List<ShqyrtuesArtikull> shqyrtuesArtikull = new ArrayList<ShqyrtuesArtikull>();
	

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

	public List<Autor> getAutoret() {
		return autoret;
	}

	public void setAutoret(List<Autor> autoret) {
		this.autoret = autoret;
	}

	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getDocPicture() {
		return docPicture;
	}
	public void setDocPicture(String docPicture) {
		this.docPicture = docPicture;
	}
	public List<ShqyrtuesArtikull> getShqyrtuesArtikull() {
		return shqyrtuesArtikull;
	}
	public void setShqyrtuesArtikull(List<ShqyrtuesArtikull> shqyrtuesArtikull) {
		this.shqyrtuesArtikull = shqyrtuesArtikull;
	}

}
