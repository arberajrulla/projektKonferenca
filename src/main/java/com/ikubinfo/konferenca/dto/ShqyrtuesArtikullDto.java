package com.ikubinfo.konferenca.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class ShqyrtuesArtikullDto {

	@NotNull(message = "Zgjidhni te pakten nje shqyrtues!")
	private String shqrtid;
	
	//@NotNull(message = "Zgjidhni te pakten nje artikull!")
	private int arid;
	
	//@NotNull(message = "Duhet te kete te pakten nje vleresim per meriten teknike")
	//@Pattern(regexp = "^[0-9]*$", message = "Zgjidhni nje vleresim !")
	private int meritaTeknike; 
	
	private int kuptueshmeria; 
	
	private int origjinaliteti; 
	
	private int perkatesiKonference;
	
	private String emriFull;
	private String artikull;
	private String rekomandim;
	private int vleresimId;
	

	public int getVleresimId() {
		return vleresimId;
	}
	public void setVleresimId(int vleresimId) {
		this.vleresimId = vleresimId;
	}
	public String getRekomandim() {
		return rekomandim;
	}
	public void setRekomandim(String rekomandim) {
		this.rekomandim = rekomandim;
	}
	public String getArtikull() {
		return artikull;
	}
	public void setArtikull(String artikull) {
		this.artikull = artikull;
	}
	public String getEmriFull() {
		return emriFull;
	}
	public void setEmriFull(String emriFull) {
		this.emriFull = emriFull;
	}
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
