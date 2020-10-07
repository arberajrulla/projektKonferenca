package com.ikubinfo.konferenca.managedBeans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.NotNull;
import org.apache.log4j.Logger;
import org.hibernate.annotations.NotFound;
import org.hibernate.validator.constraints.NotEmpty;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.file.UploadedFile;
import com.ikubinfo.konferenca.dto.ArtikullDto;
import com.ikubinfo.konferenca.filesOperations.UploadUtil;
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
	
	@ManagedProperty(value = "#{loggedUserBean}")
	LoggedUserBean loggedUserBean;

	private List<ArtikullDto> listaArtikuj;
	private ArtikullDto artikullIRi = new ArtikullDto();
	private List<ArtikullDto> artikujTeFiltruar;
	private List<ArtikullDto> selectedArtikuj;	
	
	private UploadedFile uploadedArtikull;
	private UploadedFile uploadedArtikullPicture;

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
	public UploadedFile getUploadedArtikull() {
		return uploadedArtikull;
	}
	public void setUploadedArtikull(UploadedFile uploadedArtikull) {
		this.uploadedArtikull = uploadedArtikull;
	}
	public UploadedFile getUploadedArtikullPicture() {
		return uploadedArtikullPicture;
	}
	public void setUploadedArtikullPicture(UploadedFile uploadedArtikullPicture) {
		this.uploadedArtikullPicture = uploadedArtikullPicture;
	}
	public LoggedUserBean getLoggedUserBean() {
		return loggedUserBean;
	}
	public void setLoggedUserBean(LoggedUserBean loggedUserBean) {
		this.loggedUserBean = loggedUserBean;
	}
	
	
	public void fillListaArtikuj() {
		listaArtikuj = artikullService.getArtikujLista();
	}
	
	
	public void addArtikull() {
		
		String emriRiPic = UploadUtil.nameGenerator(uploadedArtikullPicture);
		fileUpload(uploadedArtikullPicture , emriRiPic, "/resources/uploads/artikullPics");
		artikullIRi.setDocPicture(emriRiPic);
		
		String emriRiDoc = UploadUtil.nameGenerator(uploadedArtikull);
		fileUpload(uploadedArtikull, emriRiDoc, "/resources/uploads/artikullDocs");
		artikullIRi.setDocName(emriRiDoc);
		
		if(!(emriRiPic.isEmpty() || emriRiDoc.isEmpty())) {
			artikullService.addArtikull(artikullIRi);
			log.info("Artikull added succesfully");
			fillListaArtikuj();
			autorBean.artikujDropdownFill();
		}
	}
	
	public void changeArtikullDocument() {
		//log.info("emri artikullit live: " + uploadedArtikull.getFileName().toString());
		String emriVjeter = loggedUserBean.getLoggedAutor().getArtikullDto().getDocName();
		String emriRiDoc = UploadUtil.nameGenerator(uploadedArtikull);
		
		if(!(emriRiDoc.isEmpty() || emriRiDoc==null)) {
			fileUpload(uploadedArtikull, emriRiDoc, "/resources/uploads/artikullDocs");
			ArtikullDto aDto = loggedUserBean.getLoggedAutor().getArtikullDto();
			aDto.setDocName(emriRiDoc);
		
			log.info("Artikull document updated succesfully");
			docDelete(emriVjeter);
			artikullService.updateArtikull(aDto);
			loggedUserBean.getLoggedAutor().getArtikullDto().setDocName(emriRiDoc);
		}
	}

	
	public void changeArtikullPicture() {
		//log.info("emri fotos live: " + uploadedArtikullPicture.getFileName().toString());
		String emriVjeter = loggedUserBean.getLoggedAutor().getArtikullDto().getDocPicture();
		String emriRiPic = UploadUtil.nameGenerator(uploadedArtikullPicture);
		
		if(!(emriRiPic.isEmpty()) || emriRiPic==null) {
			
			fileUpload(uploadedArtikullPicture , emriRiPic, "/resources/uploads/artikullPics");
			ArtikullDto aDto = loggedUserBean.getLoggedAutor().getArtikullDto();
			aDto.setDocPicture(emriRiPic);
		
			log.info("Artikull picture updated succesfully");
			docDelete(emriVjeter);
			artikullService.updateArtikull(aDto);
			loggedUserBean.getLoggedAutor().getArtikullDto().setDocPicture(emriRiPic);
		}
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
    
    public void fileUpload(UploadedFile upFile, String emriRi, String pathFromRes) {
		UploadUtil.saveFile(upFile, emriRi, pathFromRes);
		log.info("document upload: " + upFile.getFileName());
    }
    
    public void docDelete(String emriFile) {
    	if(emriFile==null) {
    		log.error("document delete: null file name");
    	}else {
        	UploadUtil.deleteFile(emriFile);
        	log.info("document delete: " + emriFile);
    	}
    }
    
    
}
