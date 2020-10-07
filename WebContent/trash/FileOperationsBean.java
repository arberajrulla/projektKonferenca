package com.ikubinfo.konferenca.managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;
import org.primefaces.model.file.UploadedFile;

import com.ikubinfo.konferenca.dto.ArtikullDto;
import com.ikubinfo.konferenca.filesOperations.UploadUtil;
import com.ikubinfo.konferenca.service.ArtikullService;

@ManagedBean(name = "fileOperationsBean")
@RequestScoped
public class FileOperationsBean {
	
	private static Logger log = Logger.getLogger(FileOperationsBean.class);

	
	@ManagedProperty(value = "#{loggedUserBean}")
	LoggedUserBean loggedUserBean;
	
	@ManagedProperty(value = "#{autorBean}")
	AutorBean autorBean;
	
	@ManagedProperty(value = "#{artikullService}")
	ArtikullService artikullService;
	
	
	
	public LoggedUserBean getLoggedUserBean() {
		return loggedUserBean;
	}
	public void setLoggedUserBean(LoggedUserBean loggedUserBean) {
		this.loggedUserBean = loggedUserBean;
	}
	public AutorBean getAutorBean() {
		return autorBean;
	}
	public void setAutorBean(AutorBean autorBean) {
		this.autorBean = autorBean;
	}
	public ArtikullService getArtikullService() {
		return artikullService;
	}
	public void setArtikullService(ArtikullService artikullService) {
		this.artikullService = artikullService;
	}

	private UploadedFile uploadedArtikull;
	private UploadedFile uploadedArtikullPicture;
	
	
	
	public UploadedFile getUploadedArtikull() {
		return uploadedArtikull;
	}
	public void setUploadedArtikull(UploadedFile uploadedArtikull) {
		log.info("emri artikullit live: " + uploadedArtikull.getFileName().toString());
		this.uploadedArtikull = uploadedArtikull;
	}
	public UploadedFile getUploadedArtikullPicture() {
		return uploadedArtikullPicture;
	}
	public void setUploadedArtikullPicture(UploadedFile uploadedArtikullPicture) {
		this.uploadedArtikullPicture = uploadedArtikullPicture;
	}
	
	
	/*
	 * 
	 * public void addArtikull() {
	 * 
	 * String emriRiPic = UploadUtil.nameGenerator(uploadedArtikullPicture);
	 * fileUpload(uploadedArtikullPicture , emriRiPic,
	 * "/resources/uploads/artikullPics"); loggedUserBean.getLoggedAutor().
	 * artikullIRi.setDocPicture(emriRiPic);
	 * 
	 * String emriRiDoc = UploadUtil.nameGenerator(uploadedArtikull);
	 * fileUpload(uploadedArtikull, emriRiDoc, "/resources/uploads/artikullDocs");
	 * artikullIRi.setDocName(emriRiDoc);
	 * 
	 * if(!(emriRiPic.isEmpty() || emriRiDoc.isEmpty())) {
	 * artikullService.addArtikull(artikullIRi);
	 * log.info("Artikull added succesfully"); } }
	 */
	
	
	
	public void changeArtikullDocument() {
		log.info("emri artikullit live: " + uploadedArtikull.getFileName().toString());
		String emriRiDoc = UploadUtil.nameGenerator(uploadedArtikull);
		
		fileUpload(uploadedArtikull, emriRiDoc, "/resources/uploads/artikullDocs");
		ArtikullDto aDto = loggedUserBean.getLoggedAutor().getArtikullDto();
		aDto.setDocName(emriRiDoc);
		
		if(!(emriRiDoc.isEmpty() || emriRiDoc==null)) {
			
			log.info("Artikull document updated succesfully");
			docDelete(loggedUserBean.getLoggedAutor().getArtikullDto().getDocName());
			loggedUserBean.getLoggedAutor().getArtikullDto().setDocName(emriRiDoc);
		}
	}
	
	public void changeArtikullPicture() {
		
		String emriRiPic = UploadUtil.nameGenerator(uploadedArtikullPicture);
		fileUpload(uploadedArtikullPicture , emriRiPic, "/resources/uploads/artikullPics");
		ArtikullDto aDto = loggedUserBean.getLoggedAutor().getArtikullDto();
		aDto.setDocPicture(emriRiPic);
		
		if(!(emriRiPic.isEmpty()) || emriRiPic==null) {
			artikullService.updateArtikull(aDto);
			log.info("Artikull picture updated succesfully");
			docDelete(loggedUserBean.getLoggedAutor().getArtikullDto().getDocPicture());
			loggedUserBean.getLoggedAutor().getArtikullDto().setDocPicture(emriRiPic);

		}
	}
	
    public void fileUpload(UploadedFile upFile, String emriRi, String pathFromRes) {
		UploadUtil.saveFile(upFile, emriRi, pathFromRes);
		//log.info("document upload: " + upFile.getFileName());
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
