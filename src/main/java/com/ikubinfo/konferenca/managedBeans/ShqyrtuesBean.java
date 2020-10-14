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
import com.ikubinfo.konferenca.dto.ShqyrtuesArtikullDto;
import com.ikubinfo.konferenca.dto.ShqyrtuesDto;
import com.ikubinfo.konferenca.service.ShqyrtuesService;
import com.ikubinfo.konferenca.service.VleresimeService;
import com.ikubinfo.konferenca.utils.UtilMessages;

@ManagedBean(name = "shqyrtuesBean")
@ViewScoped
public class ShqyrtuesBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(ShqyrtuesBean.class);

	@ManagedProperty(value = "#{artikullBean}")
	ArtikullBean artikullBean;
	
	@ManagedProperty(value = "#{shqyrtuesService}")
	ShqyrtuesService shqyrtuesService;

	@ManagedProperty(value = "#{loggedUserBean}")
	LoggedUserBean loggedUserBean;
	
	@ManagedProperty(value = "#{vleresimeService}")
	VleresimeService vleresimeService;
	
	@ManagedProperty(value = "#{shqyrtuesHolderBean}")
	ShqyrtuesHolderBean shqyrtuesHolderBean;


	private ShqyrtuesDto selectedShqyrtues;
	private ShqyrtuesDto shqyrtuesIRi = new ShqyrtuesDto();
	private List<ShqyrtuesDto> selectedShqyrtuesa = new ArrayList<ShqyrtuesDto>();
	private List<ShqyrtuesDto> listaShqyrtues;
	private List<ShqyrtuesDto> shqyrtuesitFiltruar;

	private String docName;
	private String picName;
	
	@PostConstruct
	public void init() {
		fillListaShqyrtues();
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
	public LoggedUserBean getLoggedUserBean() {
		return loggedUserBean;
	}
	public void setLoggedUserBean(LoggedUserBean loggedUserBean) {
		this.loggedUserBean = loggedUserBean;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	public ArtikullBean getArtikullBean() {
		return artikullBean;
	}
	public void setArtikullBean(ArtikullBean artikullBean) {
		this.artikullBean = artikullBean;
	}
	public VleresimeService getVleresimeService() {
		return vleresimeService;
	}
	public void setVleresimeService(VleresimeService vleresimeService) {
		this.vleresimeService = vleresimeService;
	}
	public ShqyrtuesHolderBean getShqyrtuesHolderBean() {
		return shqyrtuesHolderBean;
	}
	public void setShqyrtuesHolderBean(ShqyrtuesHolderBean shqyrtuesHolderBean) {
		this.shqyrtuesHolderBean = shqyrtuesHolderBean;
	}


	
	public void fillListaShqyrtues() {
		listaShqyrtues = shqyrtuesService.getAllShqyrtuesList();
	}
	
	public void addShqyrtues() {
		log.info("Starting the process to add new Shqyrtues");
		
		if (!(shqyrtuesService.shqyrtuesCheck(shqyrtuesIRi.getIdEmail()))) {
			if (shqyrtuesService.addShqyrtues(shqyrtuesIRi)) {
				UtilMessages.addMessageSuccess(null, 
						UtilMessages.bundle.getString("SHQYRTUES_ADD_SUCCESS"));
				fillListaShqyrtues();
			}else {
				UtilMessages.addMessageError(null, 
						UtilMessages.bundle.getString("SHQYRTUES_ADD_FAIL"));
			}
		} else {
			UtilMessages.addMessageError(null, 
					UtilMessages.bundle.getString("SHQYRTUES_EXISTS"));
		}
	}
	
	
	
	public void deleteShqyrtues() {
		log.info("Starting the process to delete Shqyrtues list "+ selectedShqyrtuesa.toString() );
		
		if (shqyrtuesService.deleteShqyrtues(selectedShqyrtuesa)) {
			UtilMessages.addMessageSuccess(null, 
					UtilMessages.bundle.getString("SHQYRTUES_DELETE_SUCCESS"));
			fillListaShqyrtues();
		} else {
			UtilMessages.addMessageError(null, 
					UtilMessages.bundle.getString("SHQYRTUES_DELETE_FAIL"));
		}
	}
	
	
	
    public void onRowEdit(RowEditEvent<ShqyrtuesDto> event) {
		log.info("Row edit called: " + event.getObject().getEmri());
		if (shqyrtuesService.updateShqyrtues(event.getObject())) {
			UtilMessages.addMessageSuccess(null, 
					UtilMessages.bundle.getString("SHQYRTUES_UPDATE_SUCCESS"));
		} else {
			UtilMessages.addMessageError(null, 
					UtilMessages.bundle.getString("SHQYRTUES_UPDATE_FAIL"));
		}
    }
     
    
    
    public void onRowCancel(RowEditEvent<ShqyrtuesDto> event) {
    	log.info("Canceled the editing");
    	UtilMessages.addCustomMessage(null, 
    			UtilMessages.bundle.getString("INFO_CANCEL"));
    }
    
    
    
    /* Shqyrtues Panel methods*/
    
	public void setFinishRegisterAsNewShqyrtues() {
		shqyrtuesIRi.setEmri(loggedUserBean.getTemporaryLoggedUser().getEmri());
		shqyrtuesIRi.setMbiemri(loggedUserBean.getTemporaryLoggedUser().getMbiemri());
		shqyrtuesIRi.setIdEmail(loggedUserBean.getTemporaryLoggedUser().getEmail());
	}
	
	
	public String finishShqyrtuesRegistration() {
		setFinishRegisterAsNewShqyrtues();
		addShqyrtues();
		loggedUserBean.temporaryToPermanentLoggedUser();
		loggedUserBean.getLoggedUser().setRegisterStatus(1);
		loggedUserBean.updateLoggedUserDetails();
		loggedUserBean.setLoggedShqyrtues(shqyrtuesIRi);
		return "/shqyrtues_res/faqja1.xhtml?faces-redirect=true";
	}
	
	
	public String setVleresimToEdit(ShqyrtuesArtikullDto shaDto) {
		shqyrtuesHolderBean.setVleresimEdit(shaDto);
		return "/shqyrtues_res/modifikoVleresimin.xhtml?faces-redirect=true";
	}
	
	
	public String setArtikullPerTeVleresuar(ArtikullDto aDto ) {
		shqyrtuesHolderBean.setArtikullPerVleresim(aDto);
		shqyrtuesHolderBean.fillAutoretPerArtikull();
		return "/shqyrtues_res/shtoVleresim.xhtml?faces-redirect=true";
	}
	
	public void ruajVleresimIRi() {
		shqyrtuesHolderBean.getVleresimIRi().setArid(shqyrtuesHolderBean.getArtikullPerVleresim().getArtikullId());
		shqyrtuesHolderBean.getVleresimIRi().setShqrtid(loggedUserBean.getLoggedUser().getEmail());
		
		if(vleresimeService.vleresimCheck(shqyrtuesHolderBean.getVleresimIRi().getShqrtid()
				, shqyrtuesHolderBean.getVleresimIRi().getArid())) {
			UtilMessages.addMessageError(null, 
					UtilMessages.bundle.getString("ARTIKULL_ALREADY_REVIEWED"));
		}else {
			if (vleresimeService.addVleresim(shqyrtuesHolderBean.getVleresimIRi())) {
				UtilMessages.addMessageSuccess(null, 
						UtilMessages.bundle.getString("VLERESIM_ADD_SUCCESS"));
			} else {
				UtilMessages.addMessageError(null, 
						UtilMessages.bundle.getString("VLERESIM_ADD_FAIL"));
			}
		}
		shqyrtuesHolderBean.setVleresimIRi(new ShqyrtuesArtikullDto());
	}
	
	
	public void modifikoVleresimin() {
		if (vleresimeService.updateVleresim(shqyrtuesHolderBean.getVleresimEdit())) {
			UtilMessages.addMessageSuccess(null, 
					UtilMessages.bundle.getString("VLERESIM_UPDATE_SUCCESS"));
			shqyrtuesHolderBean.fillListaVleresimetEMia();
		}else {
			UtilMessages.addMessageError(null, 
					UtilMessages.bundle.getString("VLERESIM_UPDATE_FAIL"));
		}
	}
	
	
	public void fshiVleresimin(ShqyrtuesArtikullDto shaDTO) {
		if(vleresimeService.deleteSingleVleresim(shaDTO)) {
			UtilMessages.addMessageSuccess(null, 
					UtilMessages.bundle.getString("VLERESIM_DELETE_SUCCESS"));
			shqyrtuesHolderBean.fillListaVleresimetEMia();
		}else {
			UtilMessages.addMessageError(null, 
					UtilMessages.bundle.getString("VLERESIM_DELETE_FAIL"));
		}
	}
	
	
	public void documentForView(String docView) {
		this.docName = docView;
	}
	
	
	public String shqyrtuesPanelArtikuj() {
		shqyrtuesHolderBean.setListaVleresimetEMia(null);
		shqyrtuesHolderBean.setArtikujPotencialePerVlersim(null);
		return shqyrtuesHolderBean.shqyrtuesArtikujListaOpen();
	}
	
	
	public String shqyrtuesPanelVleresimetEMia() {
		shqyrtuesHolderBean.setListaVleresimetEMia(null);
		shqyrtuesHolderBean.setArtikujPotencialePerVlersim(null);
		return shqyrtuesHolderBean.shqyrtuesVleresimetEMia();
	}
	

}
