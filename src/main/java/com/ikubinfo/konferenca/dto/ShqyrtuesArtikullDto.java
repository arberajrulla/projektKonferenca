package com.ikubinfo.konferenca.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

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
	
	private ShqyrtuesDto shqyrtuesPerVleresimin;
	
	private ArtikullDto artikullPerShqyrtuesin;
	
	private List<AutorDto> listaAutorPerArtikullShqyrtues;
	

	public List<AutorDto> getListaAutorPerArtikullShqyrtues() {
		return listaAutorPerArtikullShqyrtues;
	}
	public void setListaAutorPerArtikullShqyrtues(List<AutorDto> listaAutorPerArtikullShqyrtues) {
		this.listaAutorPerArtikullShqyrtues = listaAutorPerArtikullShqyrtues;
	}
	public ArtikullDto getArtikullPerShqyrtuesin() {
		return artikullPerShqyrtuesin;
	}
	public void setArtikullPerShqyrtuesin(ArtikullDto artikullPerShqyrtuesin) {
		this.artikullPerShqyrtuesin = artikullPerShqyrtuesin;
	}
	public ShqyrtuesDto getShqyrtuesPerVleresimin() {
		return shqyrtuesPerVleresimin;
	}
	public void setShqyrtuesPerVleresimin(ShqyrtuesDto shqyrtuesPerVleresimin) {
		this.shqyrtuesPerVleresimin = shqyrtuesPerVleresimin;
	}
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
