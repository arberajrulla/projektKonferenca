package com.ikubinfo.konferenca.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="shqyrtues_artikull")
@SequenceGenerator(name= ShqyrtuesArtikull.sekuencaVleresim, sequenceName = ShqyrtuesArtikull.sekuencaVleresim ,initialValue=10, allocationSize=50)
public class ShqyrtuesArtikull implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String sekuencaVleresim = "SEQUENCE_VLERESIMI_ID";
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_VLERESIMI_ID")
	@Column(name = "vleresim_id")
	private int vleresimId;

	@Column(name = "merita_Teknike")
	private int meritaTeknike; 
	
	@Column(name = "kuptueshmeria")
	private int kuptueshmeria; 
	
	@Column(name = "origjinaliteti")
	private int origjinaliteti; 
	
	@Column(name = "perkatesi_Konference")
	private int perkatesiKonference; 
	
	@Column(name = "rekomandime")
	private String rekomandim; 

	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "shqrtid")
	private Shqyrtues shqyrtuesVleresim;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "arid")
	private Artikull artikullVleresim;
	

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
	public Shqyrtues getShqyrtuesVleresim() {
		return shqyrtuesVleresim;
	}

	public void setShqyrtuesVleresim(Shqyrtues shqyrtuesVleresim) {
		this.shqyrtuesVleresim = shqyrtuesVleresim;
	}

	public Artikull getArtikullVleresim() {
		return artikullVleresim;
	}

	public void setArtikullVleresim(Artikull artikullVleresim) {
		this.artikullVleresim = artikullVleresim;
	}
	public String getRekomandim() {
		return rekomandim;
	}
	public void setRekomandim(String rekomandim) {
		this.rekomandim = rekomandim;
	}

	public int getVleresimId() {
		return vleresimId;
	}

	public void setVleresimId(int vleresimId) {
		this.vleresimId = vleresimId;
	}
}
