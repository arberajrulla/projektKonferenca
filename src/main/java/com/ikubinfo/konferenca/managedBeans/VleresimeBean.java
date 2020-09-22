package com.ikubinfo.konferenca.managedBeans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;

import com.ikubinfo.konferenca.dto.ShqyrtuesArtikullDto;
import com.ikubinfo.konferenca.service.VleresimeService;


@ManagedBean(name = "vleresimeBean")
@RequestScoped
public class VleresimeBean {
	private static Logger log = Logger.getLogger(VleresimeBean.class);


	@ManagedProperty(value = "#{vleresimeService}")
	VleresimeService vleresimeService;
	
	
	private ShqyrtuesArtikullDto newVleresim;
	private List<ShqyrtuesArtikullDto> filteredVleresime;
	private List<ShqyrtuesArtikullDto> selectedVleresime = new ArrayList<ShqyrtuesArtikullDto>();
	private List<ShqyrtuesArtikullDto> allVleresimeList;
	

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


	
	
	
	public String adminMenuListOfVleresime() {
		allVleresimeList = vleresimeService.getShqyrtuesArtikullList();
		if(allVleresimeList==null) {
			return null;
		}else {
			return("/admin/vleresimet.xhtml?faces-redirect=true");
		}
	}
	

	
	
	
	public void addVleresim() {
		
	}
	
	public void deleteVleresim() {
		
	}
	
	public void updateVleresim() {
		
		
	}
	
}
