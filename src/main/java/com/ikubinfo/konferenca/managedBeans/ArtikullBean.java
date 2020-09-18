package com.ikubinfo.konferenca.managedBeans;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import com.ikubinfo.konferenca.dto.ArtikullDto;
import com.ikubinfo.konferenca.dto.UserDto;
import com.ikubinfo.konferenca.service.ArtikullService;

@ManagedBean(name = "artikullBean")
@RequestScoped
public class ArtikullBean {
	
	@ManagedProperty(value = "#{artikullService}")
	ArtikullService artikullService;
	
	private ArtikullDto selectedArtikull;
	private ArrayList<ArtikullDto> selectedArtikuj = new ArrayList<ArtikullDto>();

	private ArrayList<ArtikullDto> listaArtikuj;
	
	public ArrayList<ArtikullDto> getListaArtikuj() {
		return artikullService.getArtikujLista();
	}
	public void setListaArtikuj(ArrayList<ArtikullDto> listaArtikuj) {
		this.listaArtikuj = listaArtikuj;
	}
	public ArtikullService getArtikullService() {
		return artikullService;
	}
	public void setArtikullService(ArtikullService aService) {
		this.artikullService = aService;
	}
	public ArtikullDto getSelectedArtikull() {
		return selectedArtikull;
	}
	public void setSelectedArtikull(ArtikullDto selectedArtikull) {
		this.selectedArtikull = selectedArtikull;
	}
	public ArrayList<ArtikullDto> getSelectedArtikuj() {
		return selectedArtikuj;
	}
	public void setSelectedArtikuj(ArrayList<ArtikullDto> selectedArtikuj) {
		this.selectedArtikuj = selectedArtikuj;
	}
	
}
