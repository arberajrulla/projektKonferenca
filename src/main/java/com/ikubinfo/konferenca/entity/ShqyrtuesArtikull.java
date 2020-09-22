package com.ikubinfo.konferenca.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="shqyrtues_artikull")
public class ShqyrtuesArtikull {

	
	@Id
	@Column(name = "shqrtid")
	private String shqrtid;

	@Column(name = "arid")
	private int arid;

	@Column(name = "merita_Teknike")
	private int meritaTeknike; 
	
	@Column(name = "kuptueshmeria")
	private int kuptueshmeria; 
	
	@Column(name = "origjinaliteti")
	private int origjinaliteti; 
	
	@Column(name = "perkatesi_Konference")
	private int perkatesiKonference; 
	
	/*
	 * @OneToMany(mappedBy = "shqyrtuesArtikullsh", cascade=CascadeType.ALL) private
	 * List<Shqyrtues> shqyrtuesit = new ArrayList<>();
	 * 
	 * @OneToMany(mappedBy = "shqyrtuesArtikullA", cascade=CascadeType.ALL) private
	 * List<Artikull> artikujt = new ArrayList<>();
	 */
	
	public String getShqrtid() {
		return shqrtid;
	}

	public void setShqrtid(String shqrtid) {
		this.shqrtid = shqrtid;
	}

	public int getArid() {
		return arid;
	}

	public void setArid(int arid) {
		this.arid = arid;
	}

	public int getMeritaTeknike() {
		return meritaTeknike;
	}

	public void setMeritaTeknike(int meritaTeknike) {
		this.meritaTeknike = meritaTeknike;
	}

	public int getKuptueshmeria() {
		return kuptueshmeria;
	}

	public void setKuptueshmeria(int kuptueshmeria) {
		this.kuptueshmeria = kuptueshmeria;
	}

	public int getOrigjinaliteti() {
		return origjinaliteti;
	}

	public void setOrigjinaliteti(int origjinaliteti) {
		this.origjinaliteti = origjinaliteti;
	}

	public int getPerkatesiKonference() {
		return perkatesiKonference;
	}

	public void setPerkatesiKonference(int perkatesiKonference) {
		this.perkatesiKonference = perkatesiKonference;
	}
	
}
