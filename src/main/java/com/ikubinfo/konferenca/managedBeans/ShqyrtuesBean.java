package com.ikubinfo.konferenca.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;
import com.ikubinfo.konferenca.dto.ShqyrtuesDto;
import com.ikubinfo.konferenca.service.ShqyrtuesService;

@ManagedBean(name = "shqyrtuesBean")
@ViewScoped
public class ShqyrtuesBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(ShqyrtuesBean.class);

	@ManagedProperty(value = "#{shqyrtuesService}")
	ShqyrtuesService shqyrtuesService;
	
	private ShqyrtuesDto selectedShqyrtues;
	private List<ShqyrtuesDto> selectedShqyrtuesa = new ArrayList<ShqyrtuesDto>();
	private List<ShqyrtuesDto> listaShqyrtues = new ArrayList<ShqyrtuesDto>();
	private ShqyrtuesDto shqyrtuesIRi = new ShqyrtuesDto();
	private List<ShqyrtuesDto> shqyrtuesitFiltruar;
	
	
	@PostConstruct
	public void init() {
		log.info("Filling list of Shqyrtues on init!");
		listaShqyrtues = shqyrtuesService.getAllShqyrtuesList();
	}
	
	
	public ShqyrtuesService getShqyrtuesService() {
		return shqyrtuesService;
	}
	public void setShqyrtuesService(ShqyrtuesService shqyrtuesService) {
		this.shqyrtuesService = shqyrtuesService;
	}
	public ShqyrtuesDto getSelectedShqyrtues() {
		return selectedShqyrtues;
	}
	public void setSelectedShqyrtues(ShqyrtuesDto selectedShqyrtues) {
		this.selectedShqyrtues = selectedShqyrtues;
	}
	public List<ShqyrtuesDto> getSelectedShqyrtuesa() {
		return selectedShqyrtuesa;
	}
	public void setSelectedShqyrtuesa(List<ShqyrtuesDto> selectedShqyrtuesa) {
		this.selectedShqyrtuesa = selectedShqyrtuesa;
	}
	public List<ShqyrtuesDto> getListaShqyrtues() {
		return listaShqyrtues;
	}
	public void setListaShqyrtues(List<ShqyrtuesDto> listaShqyrtues) {
		this.listaShqyrtues = listaShqyrtues;
	}
	public ShqyrtuesDto getShqyrtuesIRi() {
		return shqyrtuesIRi;
	}
	public void setShqyrtuesIRi(ShqyrtuesDto shqyrtuesIRi) {
		this.shqyrtuesIRi = shqyrtuesIRi;
	}
	public List<ShqyrtuesDto> getShqyrtuesitFiltruar() {
		return shqyrtuesitFiltruar;
	}
	public void setShqyrtuesitFiltruar(List<ShqyrtuesDto> shqyrtuesitFiltruar) {
		this.shqyrtuesitFiltruar = shqyrtuesitFiltruar;
	}
	
	
	
	public void addShqyrtues() {
		log.info("Starting the process to add new Shqyrtues");
		if(shqyrtuesService.addShqyrtues(shqyrtuesIRi)) {
			log.info("New Shqyrtues was successfully added!");
		}else{
			log.error("Error, New Shqyrtues was not added!");
		}
	}
	
	
	public void deleteShqyrtues() {
		log.info("Starting the process to delete Shqyrtues list");
		if(shqyrtuesService.deleteShqyrtues(selectedShqyrtuesa)) {
			log.info("Selected Shqyrtues were successfully deleted!");
		}else{
			log.error("Error, Shqyrtues were not deleted!");
		}
	}
	
    public void onRowEdit(RowEditEvent<ShqyrtuesDto> event) {
		log.info("Row edit called: " + event.getObject().getEmri());
		
		if(shqyrtuesService.updateShqyrtues(event.getObject())) {
			log.info("User object Updated SUCCESSFULLY!");
		} else {
			log.error("User wasn't updated!");
		}
        
    }
     
    public void onRowCancel(RowEditEvent<ShqyrtuesDto> event) {
    	log.info("Canceled the editing");
    }
	
}
