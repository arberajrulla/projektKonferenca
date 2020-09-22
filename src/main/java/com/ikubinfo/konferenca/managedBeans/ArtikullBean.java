package com.ikubinfo.konferenca.managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;

import com.ikubinfo.konferenca.dto.ArtikullDto;
import com.ikubinfo.konferenca.dto.UserDto;
import com.ikubinfo.konferenca.service.ArtikullService;

@ManagedBean(name = "artikullBean")
@RequestScoped
public class ArtikullBean {
	private static Logger log = Logger.getLogger(ArtikullBean.class);
	
	@ManagedProperty(value = "#{artikullService}")
	ArtikullService artikullService;
	
	
	
	
	private List<ArtikullDto> selectedArtikuj = new ArrayList<ArtikullDto>();
	private List<ArtikullDto> listaArtikuj;
	private ArtikullDto artikullIRi;
	private List<ArtikullDto> artikujtFiltruar;
	
	@PostConstruct
	public void postConstruct() {
		listaArtikuj = artikullService.getArtikujLista();
	}
	
	
	public List<ArtikullDto> getArtikujtFiltruar() {
		return artikujtFiltruar;
	}
	public void setArtikujtFiltruar(List<ArtikullDto> artikujtFiltruar) {
		this.artikujtFiltruar = artikujtFiltruar;
	}
	public ArtikullDto getArtikullIRi() {
		return artikullIRi;
	}
	public void setArtikullIRi(ArtikullDto artikullIRi) {
		this.artikullIRi = artikullIRi;
	}
	public List<ArtikullDto> getListaArtikuj() {
		return artikullService.getArtikujLista();
	}
	public void setListaArtikuj(List<ArtikullDto> listaArtikuj) {
		this.listaArtikuj = listaArtikuj;
	}
	public ArtikullService getArtikullService() {
		return artikullService;
	}
	public void setArtikullService(ArtikullService aService) {
		this.artikullService = aService;
	}
	public List<ArtikullDto> getSelectedArtikuj() {
		return selectedArtikuj;
	}
	public void setSelectedArtikuj(List<ArtikullDto> selectedArtikuj) {
		this.selectedArtikuj = selectedArtikuj;
	}
	
	
	
	
	
	
	public String adminMenuListOfArtikuj() {
		return("/admin/artikuj.xhtml?faces-redirect=true");
	}
	
	
	
	
	
	
	
	
	
	
	public void addArtikull() {
		
	}
	
	public void deleteArtikull() {
		
	}
	
	public void editArtikull() {
		
	}
	
	
}
