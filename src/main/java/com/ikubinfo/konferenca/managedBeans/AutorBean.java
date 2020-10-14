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
import javax.validation.constraints.NotNull;

import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.file.UploadedFile;

import com.ikubinfo.konferenca.dto.ArtikullDto;
import com.ikubinfo.konferenca.dto.AutorDto;
import com.ikubinfo.konferenca.dto.ShqyrtuesArtikullDto;
import com.ikubinfo.konferenca.dto.UserDto;
import com.ikubinfo.konferenca.service.ArtikullService;
import com.ikubinfo.konferenca.service.AutorService;
import com.ikubinfo.konferenca.utils.UtilMessages;

@ManagedBean(name = "autorBean")
@ViewScoped
public class AutorBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(AutorBean.class);

	@ManagedProperty(value = "#{autorService}")
	AutorService autorService;
	
	@ManagedProperty(value = "#{artikullService}")
	ArtikullService artikullService;
	
	@ManagedProperty(value = "#{loggedUserBean}")
	LoggedUserBean loggedUserBean;

	private AutorDto newAutor = new AutorDto();
	private AutorDto selectedAutor;
	private List<AutorDto> filteredAutore;
	private List<AutorDto> selectedAutore = new ArrayList<AutorDto>();
	private List<AutorDto> allAutorList;
	private List<ArtikullDto> artikullListForNewAutor;
	private Map<String,Integer> artikujDropdown = new HashMap<String, Integer>();
	private Map<String,Integer> artikujDropdownModify = new HashMap<String, Integer>();
	private int bashkAutoreCount;


	@PostConstruct
	public void init() {
		fillAutorList();
		artikujDropdownFill();
		fillArtikujDropdownModify();
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
	public Map<String, Integer> getArtikujDropdownModify() {
		return artikujDropdownModify;
	}
	public void setArtikujDropdownModify(Map<String, Integer> artikujDropdownModify) {
		this.artikujDropdownModify = artikujDropdownModify;
	}
	public LoggedUserBean getLoggedUserBean() {
		return loggedUserBean;
	}
	public void setLoggedUserBean(LoggedUserBean loggedUserBean) {
		this.loggedUserBean = loggedUserBean;
	}
	public int getBashkAutoreCount() {
		return bashkAutoreCount;
	}
	public void setBashkAutoreCount(int bashkAutoreCount) {
		this.bashkAutoreCount = bashkAutoreCount;
	}

	
	public List<AutorDto> fillAutorList(){
		allAutorList = autorService.getAllAutore();
		return allAutorList;
	}
	
	public void fillArtikujDropdownModify(){
		artikullListForNewAutor = artikullService.getArtikujLista();
		for(ArtikullDto artikullDto: artikullListForNewAutor) {
			artikujDropdownModify.put(artikullDto.getTitulli(), artikullDto.getArtikullId());
		}
	}
	
	public void artikujDropdownFill(){
		artikullListForNewAutor = artikullService.getArtikujLista();
		
		for(ArtikullDto artikullDto: artikullListForNewAutor) {
			artikujDropdown.put(artikullDto.getTitulli(), artikullDto.getArtikullId());
		}
	}

	public void addAutor() {
		if (autorService.autorCheck(newAutor.getEmailId())) {
			UtilMessages.addMessageError(null, 
					UtilMessages.bundle.getString("AUTOR_EXISTS"));
		}else {
			if (autorService.addAutor(newAutor)) {
				UtilMessages.addMessageSuccess(null, 
						UtilMessages.bundle.getString("AUTOR_ADD_SUCCESS"));
				fillAutorList();
				artikujDropdownFill();
			} else {
				UtilMessages.addMessageError(null, 
						UtilMessages.bundle.getString("AUTOR_ADD_FAIL"));
			}
		}
	}
	
	public void deleteAutor() {
		log.info("Delete Autor called " + selectedAutore.get(0).getEmri());
		
		if (autorService.deleteAutor(selectedAutore)) {
			UtilMessages.addMessageSuccess(null, 
					UtilMessages.bundle.getString("AUTOR_DELETE_SUCCESS"));
			fillAutorList();
			artikujDropdownFill();
		} else {
			UtilMessages.addMessageError(null, 
					UtilMessages.bundle.getString("AUTOR_DELETE_FAIL"));
		}
	}
	
    public void onRowEdit(RowEditEvent<AutorDto> event) {
		log.info("Row edit called: " + event.getObject().getEmri());
		
		if (autorService.updateAutor(event.getObject())) {
			UtilMessages.addMessageSuccess(null, 
					UtilMessages.bundle.getString("AUTOR_UPDATE_SUCCESS"));
		} else {
			UtilMessages.addMessageError(null, 
					UtilMessages.bundle.getString("AUTOR_UPDATE_FAIL"));
		}
	}
     
    public void onRowCancel(RowEditEvent<AutorDto> event) {
    	log.info("Canceled the editing");
    	UtilMessages.addCustomMessage(null, 
    			UtilMessages.bundle.getString("INFO_CANCEL"));
    }
    
	public void newDialogForNewArtikull() {
		PrimeFaces current = PrimeFaces.current();
		current.executeScript("PF('dialogAutorIRI').hide()");
		current.executeScript("PF('dialogArtikullIRI').show()");
	}
	
	
	/* Autor Panel methods*/
	
	public void setFinishRegisterAsNewAutor() {
		newAutor.setEmri(loggedUserBean.getTemporaryLoggedUser().getEmri());
		newAutor.setMbiemri(loggedUserBean.getTemporaryLoggedUser().getMbiemri());
		newAutor.setEmailId(loggedUserBean.getTemporaryLoggedUser().getEmail());
	}
	
	public String finishAutorRegistration() {
		setFinishRegisterAsNewAutor();
		addAutor();
		loggedUserBean.temporaryToPermanentLoggedUser();
		loggedUserBean.getLoggedUser().setRegisterStatus(1);
		loggedUserBean.updateLoggedUserDetails();
		AutorDto autorDto = autorService.getAutor(newAutor.getEmailId());
		if(autorDto==null) {
			loggedUserBean=null;
			throw new RuntimeException(UtilMessages.bundle.getString("EXCEPTION_AUTOR_NOT_REGISTERED_WITH_USER"));
		}else {
			loggedUserBean.setLoggedAutor(autorDto);
		}
		return "/autor_res/faqja1.xhtml?faces-redirect=true";
	}
	
	public double averagePoints() {
		double shuma = 0;
		int counter = 0;
		if (loggedUserBean.getLoggedAutor()!=null
				&& loggedUserBean.getLoggedAutor().getVleresimeDto()!=null) {
			for(ShqyrtuesArtikullDto shqyrtuesArtikullDto: loggedUserBean.getLoggedAutor().getVleresimeDto()) {
				double avgTemp = 0;
				avgTemp += shqyrtuesArtikullDto.getOrigjinaliteti();
				avgTemp += shqyrtuesArtikullDto.getKuptueshmeria();
				avgTemp += shqyrtuesArtikullDto.getMeritaTeknike();
				avgTemp += shqyrtuesArtikullDto.getPerkatesiKonference();
				shuma += avgTemp/4;
				counter++;
			}	
		}
		if(counter > 0) {
			return Math.round(shuma/counter * 1) / 1.0;
		}else {
			return 0;
		}
	}
	
	public int averageStars() {
		return (int)Math.round(averagePoints());
	}
	
	
	public List<AutorDto> bashkAutore(){
		int countBashkautore=0;
		List<AutorDto> autorDtoBashkautore = new ArrayList<>();

		if (loggedUserBean.getLoggedAutor()!=null
				&& loggedUserBean.getLoggedAutor().getArtikullDto()!=null) {
			for(AutorDto au : loggedUserBean.getLoggedAutor().getArtikullDto().getAutorDtoListForArtikull()) {
				if(!(au.getEmailId().equals(loggedUserBean.getLoggedAutor().getEmailId()))) {
					autorDtoBashkautore.add(au);
					countBashkautore++;
				}
			}
		}

		bashkAutoreCount = countBashkautore;
		return autorDtoBashkautore;
	}
	
	
}
