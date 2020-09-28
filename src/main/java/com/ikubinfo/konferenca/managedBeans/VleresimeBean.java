package com.ikubinfo.konferenca.managedBeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;

import com.ikubinfo.konferenca.dto.ArtikullDto;
import com.ikubinfo.konferenca.dto.ShqyrtuesArtikullDto;
import com.ikubinfo.konferenca.dto.ShqyrtuesDto;
import com.ikubinfo.konferenca.service.ArtikullService;
import com.ikubinfo.konferenca.service.ShqyrtuesService;
import com.ikubinfo.konferenca.service.VleresimeService;


@ManagedBean(name = "vleresimeBean")
@ViewScoped
public class VleresimeBean {
	private static Logger log = Logger.getLogger(VleresimeBean.class);


	@ManagedProperty(value = "#{vleresimeService}")
	VleresimeService vleresimeService;
	
	@ManagedProperty(value = "#{artikullService}")
	ArtikullService artikullService;

	@ManagedProperty(value = "#{shqyrtuesService}")
	ShqyrtuesService shqyrtuesService;
	
	
	private ShqyrtuesArtikullDto newVleresim = new ShqyrtuesArtikullDto();
	private List<ShqyrtuesArtikullDto> filteredVleresime;
	private List<ShqyrtuesArtikullDto> selectedVleresime = new ArrayList<ShqyrtuesArtikullDto>();
	private List<ShqyrtuesArtikullDto> allVleresimeList;
	private Map<String,String> allShqyrtuesitDropdownList = new HashMap<>();
	private Map<String,Integer> allArtikujtDropdownList = new HashMap<>();
 	

	@PostConstruct
	public void init() {
		allVleresimeList = vleresimeService.getShqyrtuesArtikullList();
		
		for(ShqyrtuesDto shqyrtuesDto : shqyrtuesService.getAllShqyrtuesList()) {
			allShqyrtuesitDropdownList.put(shqyrtuesDto.getEmri() + " " + shqyrtuesDto.getMbiemri(), shqyrtuesDto.getIdEmail());
		}
		
		for(ArtikullDto artikullDto : artikullService.getArtikujLista()) {
			allArtikujtDropdownList.put(artikullDto.getTitulli(), artikullDto.getArtikullId());
		}
	}
	
	public VleresimeService getVleresimeService() {
		return vleresimeService;
	}
	public void setVleresimeService(VleresimeService vleresimeService) {
		this.vleresimeService = vleresimeService;
	}
	public ShqyrtuesArtikullDto getNewVleresim() {
		return newVleresim;
	}
	public void setNewVleresim(ShqyrtuesArtikullDto newVleresim) {
		this.newVleresim = newVleresim;
	}
	public List<ShqyrtuesArtikullDto> getFilteredVleresime() {
		return filteredVleresime;
	}
	public void setFilteredVleresime(List<ShqyrtuesArtikullDto> filteredVleresime) {
		this.filteredVleresime = filteredVleresime;
	}
	public List<ShqyrtuesArtikullDto> getSelectedVleresime() {
		return selectedVleresime;
	}
	public void setSelectedVleresime(List<ShqyrtuesArtikullDto> selectedVleresime) {
		this.selectedVleresime = selectedVleresime;
	}
	public List<ShqyrtuesArtikullDto> getAllVleresimeList() {
		return allVleresimeList;
	}
	public void setAllVleresimeList(List<ShqyrtuesArtikullDto> allVleresimeList) {
		this.allVleresimeList = allVleresimeList;
	}
	public Map<String, String> getAllShqyrtuesitDropdownList() {
		return allShqyrtuesitDropdownList;
	}

	public void setAllShqyrtuesitDropdownList(Map<String, String> allShqyrtuesitDropdownList) {
		this.allShqyrtuesitDropdownList = allShqyrtuesitDropdownList;
	}

	public Map<String, Integer> getAllArtikujtDropdownList() {
		return allArtikujtDropdownList;
	}

	public void setAllArtikujtDropdownList(Map<String, Integer> allArtikujtDropdownList) {
		this.allArtikujtDropdownList = allArtikujtDropdownList;
	}
	public ArtikullService getArtikullService() {
		return artikullService;
	}

	public void setArtikullService(ArtikullService artikullService) {
		this.artikullService = artikullService;
	}

	public ShqyrtuesService getShqyrtuesService() {
		return shqyrtuesService;
	}

	public void setShqyrtuesService(ShqyrtuesService shqyrtuesService) {
		this.shqyrtuesService = shqyrtuesService;
	}


	
	
	
	public void addVleresim() {
		log.info("Message from vleresim " + newVleresim.getMeritaTeknike());
		if(vleresimeService.addVleresim(newVleresim)) {
			log.info("Vleresim added succesfully");
			allVleresimeList = vleresimeService.getShqyrtuesArtikullList();
		}else {
			log.error("New Vleresim wasn't addded, error!");
			allVleresimeList = vleresimeService.getShqyrtuesArtikullList();
		}
	}
	
	public void deleteVleresim() {
		log.info("Delete Vleresim called " + selectedVleresime.get(0).getEmriFull());
		
		if(vleresimeService.deleteVleresim(selectedVleresime)) {
			log.info("Vleresim list DELETED SUCCESSFULLY!");
			allVleresimeList = vleresimeService.getShqyrtuesArtikullList();
		} else {
			log.error("List of Vleresim wasn't deleted!");
		}
	}
	
	
	
	
    public void onRowEdit(RowEditEvent<ShqyrtuesArtikullDto> event) {
		log.info("Row edit called: " + event.getObject().getEmriFull());
		
		if(vleresimeService.updateVleresim(event.getObject())) {
			log.info("Vleresim object Updated SUCCESSFULLY!");
		} else {
			log.error("Vleresim wasn't updated!");
		}
        
    }
     
    public void onRowCancel(RowEditEvent<ShqyrtuesArtikullDto> event) {
    	log.info("Canceled the editing");
    }
	
	
}
