package com.ikubinfo.konferenca.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import com.ikubinfo.konferenca.dto.ArtikullDto;
import com.ikubinfo.konferenca.dto.AutorDto;
import com.ikubinfo.konferenca.service.ArtikullService;
import com.ikubinfo.konferenca.service.AutorService;

@ManagedBean(name = "autorBean")
@ViewScoped
public class AutorBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(AutorBean.class);

	@ManagedProperty(value = "#{autorService}")
	AutorService autorService;
	
	@ManagedProperty(value = "#{artikullService}")
	ArtikullService artikullService;
	
	private AutorDto newAutor = new AutorDto();
	private AutorDto selectedAutor;
	private List<AutorDto> filteredAutore;
	private List<AutorDto> selectedAutore = new ArrayList<AutorDto>();
	private List<AutorDto> allAutorList;
	private List<ArtikullDto> artikullListForNewAutor;
	private Map<String,Integer> artikujDropdown = new HashMap<String, Integer>();
	
	@PostConstruct
	public void init() {
		allAutorList = autorService.getAllAutore();
		artikullListForNewAutor = artikullService.getArtikujLista();
		
		for(ArtikullDto artikullDto: artikullListForNewAutor) {
			artikujDropdown.put(artikullDto.getTitulli(), artikullDto.getArtikullId());
		}
	}		
			

	public Map<String, Integer> getArtikujDropdown() {
		return artikujDropdown;
	}
	public void setArtikujDropdown(Map<String, Integer> artikujDropdown) {
		this.artikujDropdown = artikujDropdown;
	}
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


	
	
	public void newDialogForNewArtikull() {
		PrimeFaces current = PrimeFaces.current();
		current.executeScript("PF('dialogAutorIRI').hide()");
		current.executeScript("PF('dialogArtikullIRI').show()");
	}
	

	
	public void addAutor() {
		if(autorService.addAutor(newAutor)) {
			log.info("Autor added succesfully");
		}else {
			log.error("New Autor wasn't addded, error!");
		}
	}
	
	public void deleteAutor() {
		log.info("Delete Autor called " + selectedAutore.get(0).getEmri());
		
		if(autorService.deleteAutor(selectedAutore)) {
			log.info("Autor list DELETED SUCCESSFULLY!");
			allAutorList = autorService.getAllAutore();
		} else {
			log.error("List of Autor wasn't deleted!");
		}
	}
	
    public void onRowEdit(RowEditEvent<AutorDto> event) {
		log.info("Row edit called: " + event.getObject().getEmri());
		
		if(autorService.updateAutor(event.getObject())) {
			log.info("Autor object Updated SUCCESSFULLY!");
		} else {
			log.error("Autor wasn't updated!");
		}
        
    }
     
    public void onRowCancel(RowEditEvent<AutorDto> event) {
    	log.info("Canceled the editing");
    }
	
	
	
}
