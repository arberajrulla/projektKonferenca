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
	
	private List<ArtikullDto> selectedArtikuj = new ArrayList<ArtikullDto>();
	private List<ArtikullDto> listaArtikuj;
	private ArtikullDto artikullIRi = new ArtikullDto();
	private List<ArtikullDto> artikujtFiltruar;
	
	@PostConstruct
	public void init() {
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
	
	


	
	
	public void addArtikull() {
		if(artikullService.addArtikull(artikullIRi)) {
			log.info("Artikull added succesfully");
			listaArtikuj = artikullService.getArtikujLista();
		}else {
			log.error("New Artikull wasn't addded, error!");
		}
	}
	
	public void deleteArtikull() {
		log.info("Delete Artikull called " + selectedArtikuj.toString());
		//throw new RuntimeException("Delete artikull throwed an error"); 
		
		artikullService.deleteArtikull(selectedArtikuj);
		
		log.info("Artikuj list DELETED SUCCESSFULLY!"); 
		listaArtikuj = artikullService.getArtikujLista(); 
		 
	}
	
    public void onRowEdit(RowEditEvent<ArtikullDto> event) {
		log.info("Row edit called: " + event.getObject().getTitulli());
		
		if(artikullService.updateArtikull(event.getObject())) {
			log.info("Artikull object Updated SUCCESSFULLY!");
		} else {
			log.error("Artikull wasn't updated!");
		}
        
    }
     
    public void onRowCancel(RowEditEvent<ArtikullDto> event) {
    	log.info("Canceled the editing");
    }
	
	
}
