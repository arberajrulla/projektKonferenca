package com.ikubinfo.konferenca.managedBeans;


import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;

import com.ikubinfo.konferenca.dto.ShqyrtuesDto;
import com.ikubinfo.konferenca.service.ShqyrtuesService;

@ManagedBean(name = "shqyrtuesBean")
@RequestScoped
public class ShqyrtuesBean {
	private static Logger log = Logger.getLogger(ShqyrtuesBean.class);

	@ManagedProperty(value = "#{shqyrtuesService}")
	ShqyrtuesService shqyrtuesService;
	
	private ShqyrtuesDto selectedShqyrtues;
	private List<ShqyrtuesDto> selectedShqyrtuesa = new ArrayList<ShqyrtuesDto>();
	private List<ShqyrtuesDto> listaShqyrtues;
	private ShqyrtuesDto shqyrtuesIRi;
	private List<ShqyrtuesDto> shqyrtuesitFiltruar;
	
	
	
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










	public String adminMenuListOfShqyrtues() {
		return("/admin/shqyrtuesit.xhtml?faces-redirect=true");
	}
	
	
	
	
	
	
	
	
	
	
	public void addShqyrtues() {
		
	}
	
	public void deleteShqyrtues() {
		
	}
	
	public void editShqyrtues() {
		
	}
	
	
}
