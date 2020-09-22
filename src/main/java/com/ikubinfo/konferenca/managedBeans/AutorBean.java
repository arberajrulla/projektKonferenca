package com.ikubinfo.konferenca.managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;

import com.ikubinfo.konferenca.dto.ArtikullDto;
import com.ikubinfo.konferenca.dto.AutorDto;
import com.ikubinfo.konferenca.entity.Artikull;
import com.ikubinfo.konferenca.service.ArtikullService;
import com.ikubinfo.konferenca.service.AutorService;

@ManagedBean(name = "autorBean")
@ViewScoped
public class AutorBean {
	private static Logger log = Logger.getLogger(AutorBean.class);

	@ManagedProperty(value = "#{autorService}")
	AutorService autorService;
	
	@ManagedProperty(value = "#{artikullService}")
	ArtikullService artikullService;
	
	private AutorDto newAutor;
	private AutorDto selectedAutor;
	private List<AutorDto> filteredAutore;
	private List<AutorDto> selectedAutore = new ArrayList<AutorDto>();
	private List<AutorDto> allAutorList;
	private List<ArtikullDto> artikullListForNewAutor;
	

	public ArtikullService getArtikullService() {
		return artikullService;
	}
	public void setArtikullService(ArtikullService artikullService) {
		this.artikullService = artikullService;
	}
	public List<ArtikullDto> getArtikullListForNewAutor() {
		return artikullListForNewAutor;
	}
	public void setArtikullListForNewAutor(List<ArtikullDto> artikullListForNewAutor) {
		this.artikullListForNewAutor = artikullListForNewAutor;
	}
	public List<AutorDto> getAllAutorList() {
		return allAutorList;
	}
	public void setAllAutorList(List<AutorDto> allAutorList) {
		this.allAutorList = allAutorList;
	}
	public AutorService getAutorService() {
		return autorService;
	}
	public void setAutorService(AutorService autorService) {
		this.autorService = autorService;
	}
	public AutorDto getNewAutor() {
		return newAutor;
	}
	public void setNewAutor(AutorDto newAutor) {
		this.newAutor = newAutor;
	}
	public AutorDto getSelectedAutor() {
		return selectedAutor;
	}
	public void setSelectedAutor(AutorDto selectedAutor) {
		this.selectedAutor = selectedAutor;
	}
	public List<AutorDto> getFilteredAutore() {
		return filteredAutore;
	}
	public void setFilteredAutore(List<AutorDto> filteredAutore) {
		this.filteredAutore = filteredAutore;
	}
	public List<AutorDto> getSelectedAutore() {
		return selectedAutore;
	}
	public void setSelectedAutore(List<AutorDto> selectedAutore) {
		this.selectedAutore = selectedAutore;
	}

	
	
	@PostConstruct
	public void init() {
		allAutorList = autorService.getAllAutore();
		log.info("Lista Autore per tu vendosur ne datatable: " + allAutorList.size() + " objekte");
	}		
			
			
			//return("/admin/autoret.xhtml?faces-redirect=true");
	
	
	public void getFullArtikullListForNewAutor(){
		artikullListForNewAutor = artikullService.getArtikujLista();
	}
	
	
	public void newDialogForNewArtikull() {
		PrimeFaces current = PrimeFaces.current();
		current.executeScript("PF('dialogAutorIRI').hide()");
		current.executeScript("PF('dialogArtikullIRI').show()");
	}
	
	
	
	
	
	
	public void addAutor() {
		
	}
	
	public void deleteAutor() {
		
	}
	
	public void updateAutor() {
		
		
	}
	
	
	
}
