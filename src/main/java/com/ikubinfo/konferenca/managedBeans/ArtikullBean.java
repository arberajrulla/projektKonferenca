package com.ikubinfo.konferenca.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;
import com.ikubinfo.konferenca.dto.ArtikullDto;
import com.ikubinfo.konferenca.service.ArtikullService;

@ManagedBean(name = "artikullBean")
@ViewScoped
public class ArtikullBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(ArtikullBean.class);
	
	@ManagedProperty(value = "#{artikullService}")
	ArtikullService artikullService;
	
	@ManagedProperty(value = "#{autorBean}")
	AutorBean autorBean;

	private List<ArtikullDto> listaArtikuj;
	private ArtikullDto artikullIRi = new ArtikullDto();
	private List<ArtikullDto> artikujTeFiltruar;
	private List<ArtikullDto> selectedArtikuj;	
	

	@PostConstruct
	public void init() {
		fillListaArtikuj();
	}
	
	public ArtikullService getArtikullService() {
		return artikullService;
	}
	public void setArtikullService(ArtikullService artikullService) {
		this.artikullService = artikullService;
	}
	public List<ArtikullDto> getListaArtikuj() {
		return listaArtikuj;
	}
	public void setListaArtikuj(List<ArtikullDto> listaArtikuj) {
		this.listaArtikuj = listaArtikuj;
	}
	public ArtikullDto getArtikullIRi() {
		return artikullIRi;
	}
	public void setArtikullIRi(ArtikullDto artikullIRi) {
		this.artikullIRi = artikullIRi;
	}
	public List<ArtikullDto> getArtikujTeFiltruar() {
		return artikujTeFiltruar;
	}
	public void setArtikujTeFiltruar(List<ArtikullDto> artikujTeFiltruar) {
		this.artikujTeFiltruar = artikujTeFiltruar;
	}
	public List<ArtikullDto> getSelectedArtikuj() {
		return selectedArtikuj;
	}
	public void setSelectedArtikuj(List<ArtikullDto> selectedArtikuj) {
		this.selectedArtikuj = selectedArtikuj;
		log.info("settingSelectedArtikuj " + selectedArtikuj.size());
	}
	public AutorBean getAutorBean() {
		return autorBean;
	}
	public void setAutorBean(AutorBean autorBean) {
		this.autorBean = autorBean;
	}
	
	
	public void fillListaArtikuj() {
		listaArtikuj = artikullService.getArtikujLista();
	}
	
	
	public void addArtikull() {
		artikullService.addArtikull(artikullIRi);
		log.info("Artikull added succesfully");
		fillListaArtikuj();
		autorBean.artikujDropdownFill();
	}
	
	public void deleteArtikull() {
		log.info("Delete Artikull called " + selectedArtikuj.toString() + selectedArtikuj.size());
		artikullService.deleteArtikull(selectedArtikuj);
		fillListaArtikuj();
	}
	
    public void onRowEdit(RowEditEvent<ArtikullDto> event) {
		log.info("Row edit called: " + event.getObject().getTitulli());
		artikullService.updateArtikull(event.getObject());
    }
     
    public void onRowCancel(RowEditEvent<ArtikullDto> event) {
    	log.info("Canceled the editing");
    }
	
	
}
